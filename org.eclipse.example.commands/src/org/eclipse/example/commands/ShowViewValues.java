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

import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.commands.IParameterValues;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.views.IViewDescriptor;
import org.eclipse.ui.views.IViewRegistry;

/**
 * @since 1.0
 * 
 */
public class ShowViewValues implements IParameterValues {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.core.commands.IParameterValues#getParameterValues()
	 */
	public Map getParameterValues() {
		final Map values = new HashMap();

		IViewRegistry registry = PlatformUI.getWorkbench().getViewRegistry();
		for (IViewDescriptor view : registry.getViews()) {
			values.put(view.getLabel(), view.getId());
		}

		return values;
	}

}
