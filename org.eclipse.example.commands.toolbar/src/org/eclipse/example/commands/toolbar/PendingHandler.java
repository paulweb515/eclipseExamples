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

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExecutableExtension;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.commands.ICommandService;
import org.eclipse.ui.handlers.HandlerUtil;

public class PendingHandler extends AbstractHandler implements IExecutableExtension {

	private static final String DELIVER_CMD = "org.eclipse.example.commands.toolbar.deliver";
	private static final String REBASE_CMD = "org.eclipse.example.commands.toolbar.rebase";
	private static final String SET_CMD = "org.eclipse.example.commands.toolbar.set";
	private boolean value = false;
	
	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		Activator.getDefault().getManager().setPending(value);
		final IWorkbenchWindow window = HandlerUtil.getActiveWorkbenchWindowChecked(event);
		window.getShell().getDisplay().asyncExec(new Runnable() {
			@Override
			public void run() {
				ICommandService cs = (ICommandService) window.getService(ICommandService.class);
				cs.refreshElements(DELIVER_CMD, null);
				cs.refreshElements(REBASE_CMD, null);
				cs.refreshElements(SET_CMD, null);
			} });
		return null;
	}

	@Override
	public void setInitializationData(IConfigurationElement config,
			String propertyName, Object data) throws CoreException {
		if ("true".equals(data)) {
			value = true;
		}
	}

}
