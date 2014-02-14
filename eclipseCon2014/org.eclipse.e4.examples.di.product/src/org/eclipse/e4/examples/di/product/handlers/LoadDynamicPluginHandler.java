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

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.inject.Named;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.core.di.annotations.Optional;
import org.eclipse.e4.core.di.extensions.OSGiBundle;
import org.eclipse.e4.core.services.log.Logger;
import org.eclipse.e4.examples.di.product.internal.Activator;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.BundleException;

public class LoadDynamicPluginHandler {
	private static final String PLUGIN_LOCATION_PARAMETER = "org.eclipse.e4.examples.di.product.pluginLocation";

	@Execute
	public void execute(@OSGiBundle BundleContext bundleContext,
			@Named(PLUGIN_LOCATION_PARAMETER) String pluginLocation,
			@Optional Logger logger) {
		try {
			URL fileURL = FileLocator.toFileURL(new URL(pluginLocation));
			String refLocation = "reference:" + fileURL.toExternalForm();
			Bundle installedBundle = bundleContext.installBundle(refLocation);
			installedBundle.start(Bundle.START_TRANSIENT);
			Activator.getDefault().addDynamicBundle(installedBundle);
		} catch (BundleException e) {
			if (logger != null) {
				logger.warn(e, "Failed to load plugin from: " + pluginLocation);
			}
		} catch (MalformedURLException e) {
			if (logger != null) {
				logger.warn(e, "Failed to load plugin from: " + pluginLocation);
			}
		} catch (IOException e) {
			if (logger != null) {
				logger.warn(e, "Failed to load plugin from: " + pluginLocation);
			}
		}
	}
}
