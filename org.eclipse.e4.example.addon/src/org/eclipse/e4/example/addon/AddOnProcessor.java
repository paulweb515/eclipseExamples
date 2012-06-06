package org.eclipse.e4.example.addon;

import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.model.application.MAddon;
import org.eclipse.e4.ui.model.application.MApplication;
import org.eclipse.e4.ui.model.application.impl.ApplicationFactoryImpl;
import org.eclipse.e4.ui.model.application.impl.ApplicationPackageImpl;

public class AddOnProcessor {

	private static final String ID = "org.eclipse.e4.example.addon.AddOnProcessor";

	@Execute
	void process(MApplication application, IEclipseContext context) {
		MAddon existing = null;
		for (MAddon addon : application.getAddons()) {
			if (ID.equals(addon.getElementId())) {
				existing = addon;
				break;
			}
		}
		if (existing == null) {
			MAddon addon = ApplicationFactoryImpl.eINSTANCE.createAddon();
			addon.setElementId(ID);
			addon.setContributionURI("bundleclass://org.eclipse.e4.example.addon/org.eclipse.e4.example.addon.MenuContributionProcessingAddon");
			application.getAddons().add(addon);
		}
	}

}
