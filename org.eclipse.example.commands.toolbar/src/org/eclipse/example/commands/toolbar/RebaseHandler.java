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

import java.util.Map;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.commands.IElementUpdater;
import org.eclipse.ui.menus.UIElement;

public class RebaseHandler extends AbstractHandler implements IElementUpdater {

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateElement(UIElement element, Map parameters) {
		ImageManager manager = Activator.getDefault().getManager();
		ImageDescriptor desc = manager.rebase;
		if (manager.isPending()) {
			desc = manager.blink;
		}
		element.setIcon(desc);
	}
}
