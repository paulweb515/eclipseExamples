package org.eclipse.e4.examples.di.product.parts;

import java.util.List;
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
		control.getDisplay().asyncExec(new Runnable() {
			@Override
			public void run() {
				for (IConfigurationElement element : elements) {
					if (SamplePart.ELEMENT_AUTHOR.equals(element.getName())) {
						authors.add(new AuthorCompany(element
								.getAttribute(SamplePart.ATTR_NAME), element
								.getAttribute(SamplePart.ATTR_COMPANY)));
					}
				}
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
