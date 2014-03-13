package org.eclipse.e4.examples.di.product.parts;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.PreDestroy;
import javax.inject.Inject;
import javax.inject.Named;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.e4.core.di.annotations.Optional;
import org.eclipse.e4.examples.di.extensions.Extension;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.widgets.Control;

public class ExtensionReader {
	@Inject
	@Named("myList")
	private Set<AuthorCompany> authors;

	@Inject
	@Named("myViewer")
	private TableViewer viewer;

	private Map<IConfigurationElement, AuthorCompany> registryToCompany = new HashMap<IConfigurationElement, AuthorCompany>();

	@Inject
	@Optional
	public void setExtensions(
			final @Extension(SamplePart.EXTENSION_POINT) List<IConfigurationElement> elements) {
		if (elements == null) {
			return;
		}
		Control control = viewer.getControl();
		if (control.isDisposed()) {
			return;
		}

		HashSet<IConfigurationElement> toRemove = new HashSet<IConfigurationElement>(
				registryToCompany.keySet());
		toRemove.removeAll(elements);
		HashSet<IConfigurationElement> toAdd = new HashSet<IConfigurationElement>(
				elements);
		toAdd.removeAll(registryToCompany.keySet());

		for (IConfigurationElement element : toAdd) {
			if (SamplePart.ELEMENT_AUTHOR.equals(element.getName())) {
				AuthorCompany contributor = new AuthorCompany(
						element.getAttribute(SamplePart.ATTR_NAME),
						element.getAttribute(SamplePart.ATTR_COMPANY));
				registryToCompany.put(element, contributor);
				authors.add(contributor);
			}
		}

		for (IConfigurationElement element : toRemove) {
			AuthorCompany contributor = registryToCompany.remove(element);
			if (contributor != null) {
				authors.remove(contributor);
			}
		}
		control.getDisplay().asyncExec(new Runnable() {
			@Override
			public void run() {
				if (!viewer.getControl().isDisposed()) {
					viewer.refresh();
				}
			}
		});
	}

	@PreDestroy
	public void dispose() {
		authors.clear();
	}
}
