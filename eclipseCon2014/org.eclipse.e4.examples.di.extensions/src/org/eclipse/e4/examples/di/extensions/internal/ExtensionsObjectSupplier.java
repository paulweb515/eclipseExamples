package org.eclipse.e4.examples.di.extensions.internal;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.IRegistryEventListener;
import org.eclipse.e4.core.di.InjectionException;
import org.eclipse.e4.core.di.annotations.Optional;
import org.eclipse.e4.core.di.suppliers.ExtendedObjectSupplier;
import org.eclipse.e4.core.di.suppliers.IObjectDescriptor;
import org.eclipse.e4.core.di.suppliers.IRequestor;
import org.eclipse.e4.examples.di.extensions.Extension;

public class ExtensionsObjectSupplier extends ExtendedObjectSupplier {
	@Inject
	private IExtensionRegistry registry;

	@Override
	public Object get(IObjectDescriptor descriptor, IRequestor requestor,
			boolean track, boolean group) {

		final Class<?> desiredType = getDesiredClass(descriptor
				.getDesiredType());
		if (List.class.isAssignableFrom(desiredType)) {
			Extension qualifier = descriptor.getQualifier(Extension.class);
			String extensionPoint = qualifier.value();
			if (track) {
				addExtensionPointListener(extensionPoint, requestor);
			}
			IExtensionPoint ep = registry.getExtensionPoint(extensionPoint);
			if (ep != null) {
				return Arrays.asList(ep.getConfigurationElements());
			} else if (descriptor.getQualifier(Optional.class) != null) {
				return null;
			}
			throw new InjectionException("Failed to find Extension Point: "
					+ extensionPoint);
		}

		// Annotation used with unsupported type
		return null;
	}

	private class RegistryListener implements IRegistryEventListener {
		IRequestor requestor;
		String extensionPoint;

		public RegistryListener(IRequestor r, String ep) {
			requestor = r;
			extensionPoint = ep;
		}

		private void removeListener() {
			registry.removeListener(this);
			Map<String, RegistryListener> l = listeners.remove(requestor);
			if (l != null) {
				for (RegistryListener listener : l.values()) {
					registry.removeListener(listener);
					;
				}
			}
		}

		@Override
		public void added(IExtension[] extensions) {
			if (!requestor.isValid()) {
				removeListener();
				return;
			}
			requestor.resolveArguments(false);
			requestor.execute();
		}

		@Override
		public void removed(IExtension[] extensions) {
			if (!requestor.isValid()) {
				removeListener();
				return;
			}
			requestor.resolveArguments(false);
			requestor.execute();
		}

		@Override
		public void added(IExtensionPoint[] extensionPoints) {
			// this is a no-op
		}

		@Override
		public void removed(IExtensionPoint[] extensionPoints) {
			// this is a no-op
		}
	}

	Map<IRequestor, Map<String, RegistryListener>> listeners = new HashMap<IRequestor, Map<String, RegistryListener>>();

	private void addExtensionPointListener(String extensionPoint,
			IRequestor requestor) {
		RegistryListener listener = new RegistryListener(requestor,
				extensionPoint);
		registry.addListener(listener, extensionPoint);
		Map<String, RegistryListener> map = listeners.get(requestor);
		if (map == null) {
			map = new HashMap<String, RegistryListener>();
			listeners.put(requestor, map);
		}
		if (!map.containsKey(extensionPoint)) {
			map.put(extensionPoint, listener);
		}
	}

	private Class<?> getDesiredClass(Type desiredType) {
		if (desiredType instanceof Class<?>)
			return (Class<?>) desiredType;
		if (desiredType instanceof ParameterizedType) {
			Type rawType = ((ParameterizedType) desiredType).getRawType();
			if (rawType instanceof Class<?>)
				return (Class<?>) rawType;
		}
		return null;
	}
}
