/*******************************************************************************
 * Copyright (c) 2011 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 ******************************************************************************/

package org.eclipse.example.commands;

import org.eclipse.ui.ISourceProvider;
import org.eclipse.ui.services.AbstractServiceFactory;
import org.eclipse.ui.services.IServiceLocator;
import org.eclipse.ui.services.ISourceProviderService;

/**
 * @since 1.0
 * 
 */
public class CountServiceFactory extends AbstractServiceFactory {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.ui.services.AbstractServiceFactory#create(java.lang.Class,
	 * org.eclipse.ui.services.IServiceLocator,
	 * org.eclipse.ui.services.IServiceLocator)
	 */
	public Object create(Class serviceInterface, IServiceLocator parentLocator, IServiceLocator locator) {
		if (!ICountService.class.equals(serviceInterface)) {
			return null;
		}
		Object parent = parentLocator.getService(serviceInterface);
		if (parent == null) {
			// we're registering the global service
			ISourceProviderService sps = (ISourceProviderService) locator.getService(ISourceProviderService.class);
			if (sps == null) {
				return null;
			}
			ISourceProvider sourceProvider = sps.getSourceProvider(ICountService.COUNT);
			if (sourceProvider == null) {
				return null;
			}
			return new MyCountService(sourceProvider);
		}
		return null;
	}

}
