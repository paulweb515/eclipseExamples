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


package org.eclipse.example.commands.toolbar;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.plugin.AbstractUIPlugin;

public class ImageManager {
	ImageDescriptor deliver;
	ImageDescriptor rebase;
	ImageDescriptor set;
	ImageDescriptor blink;
	private boolean pending;

	public ImageManager() {
		deliver = AbstractUIPlugin.imageDescriptorFromPlugin(Activator.PLUGIN_ID, "icons/redo_edit.gif");
		rebase = AbstractUIPlugin.imageDescriptorFromPlugin(Activator.PLUGIN_ID, "icons/undo_edit.gif");
		set = AbstractUIPlugin.imageDescriptorFromPlugin(Activator.PLUGIN_ID, "icons/paste_edit.gif");
		blink = AbstractUIPlugin.imageDescriptorFromPlugin(Activator.PLUGIN_ID, "icons/new_wiz.gif");
	}
	
	public boolean isPending() {
		return pending;
	}
	
	public void setPending(boolean b) {
		pending = b;
	}
}
