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

import org.eclipse.core.commands.AbstractHandlerWithState;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.State;
import org.eclipse.jface.commands.PersistentState;

/**
 * @since 1.0
 *
 */
public class KeepCountHandler extends AbstractHandlerWithState {

	/* (non-Javadoc)
	 * @see org.eclipse.core.commands.IStateListener#handleStateChange(org.eclipse.core.commands.State, java.lang.Object)
	 */
	public void handleStateChange(State state, Object oldValue) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see org.eclipse.core.commands.IHandler#execute(org.eclipse.core.commands.ExecutionEvent)
	 */
	public Object execute(ExecutionEvent event) throws ExecutionException {
		State count = getState("keepCount.count");
		if (count == null) {
			count = event.getCommand().getState("keepCount.count");
		}
		if (count == null) {
			throw new ExecutionException("Have no count state");
		}
		if (count instanceof PersistentState) {
			((PersistentState)count).setShouldPersist(true);
		}
		String val = (String) count.getValue();
		if (val == null || val.length()==0) {
			val = "0";
		}
		String rc = increment(val);
		count.setValue(rc);
		System.out.println("New count: " + rc);
		return null;
	}

	private String increment(String val) {
		int i = Integer.parseInt(val);
		return "" + (i+1);
	}

	public Object execute2(ExecutionEvent event) {
		State count = event.getCommand().getState("keepCount.count");
		String val = (String) count.getValue();
		// check the val data
		String rc = increment(val);
		count.setValue(rc);
		System.out.println("New count: " + rc);
		return null;
	}
}
