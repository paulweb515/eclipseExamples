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

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.ui.handlers.HandlerUtil;

/**
 * @since 1.0
 * 
 */
public class SetPerspectiveHandler extends AbstractHandler {
	public static final String PERSP_ID = "org.eclipse.example.commands.perspectiveId";

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.core.commands.IHandler#execute(org.eclipse.core.commands.
	 * ExecutionEvent)
	 */
	public Object execute(ExecutionEvent event) throws ExecutionException {
		String perspectiveId = event.getParameter(PERSP_ID);
		if (perspectiveId == null) {
			MessageDialog.openInformation(HandlerUtil.getActiveWorkbenchWindowChecked(event).getShell(), "Open",
					"Please choose a perspective");
		} else {
			System.out.println("Set perspective: " + perspectiveId);
		}
		return null;
	}

}
