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

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.menus.WorkbenchWindowControlContribution;

public class CurrentSetContribution extends WorkbenchWindowControlContribution {

	public CurrentSetContribution() {
		// TODO Auto-generated constructor stub
	}

	public CurrentSetContribution(String id) {
		super(id);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected Control createControl(Composite parent) {
		Combo combo = new Combo(parent, SWT.DROP_DOWN);
		combo.add("Set one");
		combo.add("Set two");
		combo.add("Set Three");
		combo.select(1);
		return combo;
	}

}
