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
package org.eclipse.e4.examples.di.product.internal;

import java.util.HashSet;
import java.util.Set;

import org.eclipse.core.runtime.Plugin;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.BundleException;

public class Activator extends Plugin {
	private static Activator plugin;

	public static Activator getDefault() {
		return plugin;
	}

	private Set<Bundle> bundles = new HashSet<Bundle>();

	@Override
	public void start(BundleContext context) throws Exception {
		super.start(context);
		plugin = this;
	}

	@Override
	public void stop(BundleContext context) throws Exception {
		clearDynamicBundles();
		plugin = null;
		super.stop(context);
	}

	public void addDynamicBundle(Bundle b) {
		bundles.add(b);
	}

	public void removeDynamicBundle(Bundle b) {
		bundles.remove(b);
	}

	private void clearDynamicBundles() {
		for (Bundle bundle : bundles) {
			try {
				bundle.uninstall();
			} catch (BundleException e) {
				e.printStackTrace();
			}
		}
		bundles.clear();
	}
}
