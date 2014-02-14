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
