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

import java.util.ArrayList;
import java.util.HashMap;

import org.eclipse.core.expressions.IEvaluationContext;
import org.eclipse.jface.action.IContributionItem;
import org.eclipse.ui.IPerspectiveDescriptor;
import org.eclipse.ui.ISources;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.actions.CompoundContributionItem;
import org.eclipse.ui.menus.CommandContributionItem;
import org.eclipse.ui.menus.CommandContributionItemParameter;
import org.eclipse.ui.menus.IWorkbenchContribution;
import org.eclipse.ui.services.IEvaluationService;
import org.eclipse.ui.services.IServiceLocator;

/**
 * @since 1.0
 * 
 */
public class DynamicPerspectiveList extends CompoundContributionItem implements IWorkbenchContribution {
	private static final String SET_PERSP_ID = "org.eclipse.example.commands.setPerspective";

	private IServiceLocator locator;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.ui.actions.CompoundContributionItem#getContributionItems()
	 */
	@Override
	protected IContributionItem[] getContributionItems() {
		ArrayList<IContributionItem> list = new ArrayList<IContributionItem>();
		IEvaluationService evalService = (IEvaluationService) locator.getService(IEvaluationService.class);
		IEvaluationContext appState = evalService.getCurrentState();
		Object obj = appState.getVariable(ISources.ACTIVE_WORKBENCH_WINDOW_NAME);
		if (obj instanceof IWorkbenchWindow) {
			IWorkbenchWindow window = (IWorkbenchWindow) obj;
			for (IPerspectiveDescriptor desc : window.getActivePage().getOpenPerspectives()) {
				list.add(createItem(desc));
			}
		}
		return list.toArray(new IContributionItem[list.size()]);
	}

	private IContributionItem createItem(IPerspectiveDescriptor desc) {
		CommandContributionItemParameter parm = new CommandContributionItemParameter(locator, desc.getId(),
				SET_PERSP_ID, CommandContributionItem.STYLE_PUSH);
		HashMap<String, String> cmdParms = new HashMap<String, String>();
		cmdParms.put(SetPerspectiveHandler.PERSP_ID, desc.getId());
		parm.parameters = cmdParms;
		parm.icon = desc.getImageDescriptor();
		parm.label = desc.getLabel();
		return new CommandContributionItem(parm);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.ui.menus.IWorkbenchContribution#initialize(org.eclipse.ui
	 * .services.IServiceLocator)
	 */
	@Override
	public void initialize(IServiceLocator serviceLocator) {
		locator = serviceLocator;
	}

}
