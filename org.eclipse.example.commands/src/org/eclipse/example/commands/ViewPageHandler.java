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
import org.eclipse.ui.handlers.HandlerUtil;
import org.eclipse.ui.handlers.RadioState;

/**
 * @since 1.0
 *
 */
public class ViewPageHandler extends AbstractHandler {
	private static final String PAGE = "page";
	private static final String OUTLINE = "outline";
	private static final String NOTES = "notes";
	private static final String MASTER = "master";

	/* (non-Javadoc)
	 * @see org.eclipse.core.commands.IHandler#execute(org.eclipse.core.commands.ExecutionEvent)
	 */
	public Object execute(ExecutionEvent event) throws ExecutionException {
		if (HandlerUtil.matchesRadioState(event)) {
			return null;
		}
		String currentState = event.getParameter(RadioState.PARAMETER_ID);
		// do something
		System.out.println("Now boarding: " + currentState);
		HandlerUtil.updateRadioState(event.getCommand(), currentState);
		return null;
	}

}
