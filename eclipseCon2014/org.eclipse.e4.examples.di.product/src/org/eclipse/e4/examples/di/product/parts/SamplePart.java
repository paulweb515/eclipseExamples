/*******************************************************************************
 * Copyright (c) 2010 - 2013 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *     Lars Vogel <lars.Vogel@gmail.com> - Bug 419770
 *******************************************************************************/
package org.eclipse.e4.examples.di.product.parts;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Inject;

import org.eclipse.e4.core.contexts.ContextInjectionFactory;
import org.eclipse.e4.core.contexts.EclipseContextFactory;
import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.e4.ui.di.Focus;
import org.eclipse.e4.ui.di.Persist;
import org.eclipse.e4.ui.model.application.ui.MDirtyable;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerComparator;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

public class SamplePart {
	public static final String EXTENSION_POINT = "org.eclipse.e4.examples.di.product.authors";
	public static final String ELEMENT_AUTHOR = "author";
	public static final String ATTR_NAME = "name";
	public static final String ATTR_COMPANY = "companyName";

	private static class AuthorLabelProvider extends LabelProvider {
		@Override
		public String getText(Object element) {
			if (element instanceof AuthorCompany) {
				AuthorCompany ac = (AuthorCompany) element;
				return ac.name + ", " + ac.company;
			}
			return super.getText(element);
		}
	}

	private Text txtInput;
	private TableViewer tableViewer;
	private Set<AuthorCompany> pluginAuthors = new HashSet<AuthorCompany>();

	@Inject
	private IEclipseContext context;

	@Inject
	private MDirtyable dirty;

	private OldExtensionReader reader;
	private ExtensionReader extensionReader;

	@PostConstruct
	public void createComposite(Composite parent) {
		parent.setLayout(new GridLayout(1, false));

		txtInput = new Text(parent, SWT.BORDER);
		txtInput.setMessage("Enter text to mark part as dirty");
		txtInput.addModifyListener(new ModifyListener() {
			@Override
			public void modifyText(ModifyEvent e) {
				dirty.setDirty(true);
			}
		});
		txtInput.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		Label info = new Label(parent, SWT.NONE);
		info.setText("Plug-in Authors:");

		tableViewer = new TableViewer(parent);

		tableViewer.setLabelProvider(new AuthorLabelProvider());
		tableViewer.setContentProvider(new IStructuredContentProvider() {

			@Override
			public void inputChanged(Viewer viewer, Object oldInput,
					Object newInput) {
				// TODO Auto-generated method stub

			}

			@Override
			public void dispose() {
				// TODO Auto-generated method stub

			}

			@Override
			public Object[] getElements(Object inputElement) {
				if (inputElement instanceof Collection) {
					return ((Collection) inputElement).toArray();
				}
				return null;
			}
		});
		tableViewer.getTable().setLayoutData(new GridData(GridData.FILL_BOTH));
		ViewerComparator comparator = new ViewerComparator();
		tableViewer.setComparator(comparator);

		createLegacyReader();
		// createDiReader();

		tableViewer.setInput(pluginAuthors);
	}

	private void createLegacyReader() {
		reader = new OldExtensionReader(pluginAuthors, tableViewer);
		reader.process();
	}

	private void createDiReader() {
		IEclipseContext staticContext = EclipseContextFactory.create();
		staticContext.set("myViewer", tableViewer);
		staticContext.set("myList", pluginAuthors);
		extensionReader = ContextInjectionFactory.make(ExtensionReader.class,
				context, staticContext);
		staticContext.dispose();
	}

	@Focus
	public void setFocus() {
		tableViewer.getTable().setFocus();
	}

	@Persist
	public void save() {
		dirty.setDirty(false);
	}

	@PreDestroy
	public void dispose() {
		if (reader != null) {
			reader.dispose();
		}
	}
}
