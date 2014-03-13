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
package org.eclipse.e4.examples.di.product.handlers;

import javax.inject.Named;

import org.eclipse.core.runtime.Platform;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.core.di.annotations.Optional;
import org.eclipse.e4.core.di.extensions.OSGiBundle;
import org.eclipse.e4.core.services.log.Logger;
import org.eclipse.e4.examples.di.product.internal.Activator;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.BundleException;

public class UnloadPluginHandler {
	private static final String PLUGIN_ID_PARAMETER = "org.eclipse.e4.examples.di.product.pluginId";

	@Execute
	public void execute(@OSGiBundle BundleContext bundleContext,
			@Named(PLUGIN_ID_PARAMETER) String pluginId, @Optional Logger logger) {
		Bundle bundle = Platform.getBundle(pluginId);
		if (bundle != null) {
			try {
				bundle.uninstall();
				Activator.getDefault().removeDynamicBundle(bundle);
			} catch (BundleException e) {
				logger.warn(
						e,
						"Failed to uninstall bundle "
								+ bundle.getSymbolicName());
			}
		}
	}
}
