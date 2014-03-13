package org.eclipse.e4.examples.di.product.parts;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.IRegistryEventListener;
import org.eclipse.jface.viewers.TableViewer;
import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;
import org.osgi.util.tracker.ServiceTracker;

public class OldExtensionReader implements IRegistryEventListener {
	private Set<AuthorCompany> authors;
	private TableViewer viewer;
	private ServiceTracker<IExtensionRegistry, IExtensionRegistry> tracker;
	private IExtensionRegistry extensionRegistry;
	private Map<IConfigurationElement, AuthorCompany> registryToCompany = new HashMap<IConfigurationElement, AuthorCompany>();

	public OldExtensionReader(Set<AuthorCompany> authors, TableViewer viewer) {
		this.authors = authors;
		this.viewer = viewer;
		Bundle bundle = FrameworkUtil.getBundle(OldExtensionReader.class);
		tracker = new ServiceTracker<IExtensionRegistry, IExtensionRegistry>(
				bundle.getBundleContext(), IExtensionRegistry.class, null);
		tracker.open();
		extensionRegistry = tracker.getService();
	}

	public void process() {
		IExtensionPoint ep = extensionRegistry
				.getExtensionPoint(SamplePart.EXTENSION_POINT);
		IExtension[] extensions = ep.getExtensions();
		for (IExtension extension : extensions) {
			addAuthors(extension);
		}
		viewer.refresh();
		extensionRegistry.addListener(this, SamplePart.EXTENSION_POINT);
	}

	public void dispose() {
		extensionRegistry.removeListener(this);
		authors.clear();
	}

	private void addAuthors(IExtension extension) {
		for (IConfigurationElement element : extension
				.getConfigurationElements()) {
			if (SamplePart.ELEMENT_AUTHOR.equals(element.getName())) {
				AuthorCompany contributor = new AuthorCompany(
						element.getAttribute(SamplePart.ATTR_NAME),
						element.getAttribute(SamplePart.ATTR_COMPANY));
				registryToCompany.put(element, contributor);
				authors.add(contributor);
			}
		}
	}

	private void removeAuthors(IExtension extension) {
		for (IConfigurationElement element : extension
				.getConfigurationElements()) {
			AuthorCompany contributor = registryToCompany.remove(element);
			if (contributor != null) {
				authors.remove(contributor);
			}
		}
	}

	@Override
	public void added(final IExtension[] extensions) {
		for (IExtension extension : extensions) {
			addAuthors(extension);
		}
		viewer.getControl().getDisplay().asyncExec(new Runnable() {
			@Override
			public void run() {
				viewer.refresh();
			}
		});
	}

	@Override
	public void removed(final IExtension[] extensions) {
		for (IExtension extension : extensions) {
			removeAuthors(extension);
		}
		viewer.getControl().getDisplay().asyncExec(new Runnable() {
			@Override
			public void run() {
				viewer.refresh();
			}
		});
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
