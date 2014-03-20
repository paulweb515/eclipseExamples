/*******************************************************************************
 * Copyright (c) 2014 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.e4.examples.di.extensions.internal;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import org.eclipse.e4.core.di.suppliers.ExtendedObjectSupplier;
import org.eclipse.e4.core.di.suppliers.IObjectDescriptor;
import org.eclipse.e4.core.di.suppliers.IRequestor;

// TODO 1.2.1 Provide the extension object supplier
// TODO 1.2.2 Provide this implementation as an OSGi Service
// TODO 1.2.3 Open the file /org.eclipse.e4.examples.di.extensions/OSGI-INF/extensions.xml
// TODO 1.2.4 Provide the service, implementation, and property
// TODO 1.2.5 Open the file /org.eclipse.e4.examples.di.extensions/META-INF/MANIFEST.MF
// TODO 1.2.6 Add the Service-Component header
public class ExtensionsObjectSupplier extends ExtendedObjectSupplier {

	// TODO 1.4 Return the currently valid IConfigurationElements
	// TODO 1.4.1 get the IExtensionRegistry

	@Override
	public Object get(IObjectDescriptor descriptor, IRequestor requestor,
			boolean track, boolean group) {

		final Class<?> desiredType = getDesiredClass(descriptor
				.getDesiredType());
		// We will pass through a list
		if (List.class.isAssignableFrom(desiredType)) {
			// TODO 1.4.2 get the annotation information

			// TODO 1.5 track the extension point

			// TODO 1.4.3 return the extension point information

			// TODO 1.4.5 handle the request for optional information

			// TODO 1.4.4 throw an exception if it can't be satisfied
		}

		// Annotation used with unsupported type
		return null;
	}

	// TODO 1.5.1 add a registry listener
	// TODO 1.5.2 save the requestor info
	// TODO 1.5.2 re-evalute the requestor
	// TODO 1.5.3 if the requestor is no longer valid, remove the listener
	// private class RegistryListener implements IRegistryEventListener {
	// }

	// TODO 1.6 remove all listeners for a requestor
	// private Map<IRequestor, Set<RegistryListener>> requestors = new
	// HashMap<IRequestor, Set<RegistryListener>>();

	// private void addExtensionPointListener(String extensionPoint,
	// IRequestor requestor) {
	// TODO 1.5.4 Add the registry listener

	// TODO 1.6.1 store the listeners for a requestor

	// TODO 1.6.2 remote the listeners in removeListener()
	// }

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
