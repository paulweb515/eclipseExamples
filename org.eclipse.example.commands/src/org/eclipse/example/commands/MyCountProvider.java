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

import org.eclipse.ui.AbstractSourceProvider;
import org.eclipse.ui.ISources;

/**
 * @since 1.0
 * 
 */
public class MyCountProvider extends AbstractSourceProvider {
	private static final String[] PROVIDED_SOURCE_NAMES = new String[] { ICountService.COUNT };

	private int count = 0;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.ISourceProvider#dispose()
	 */
	public void dispose() {
	}

	public void increment() {
		count++;
		fireSourceChanged(ISources.ACTIVE_CONTEXT, ICountService.COUNT, Integer.valueOf(count));
	}
	
	public int getCount() {
		return count;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.ISourceProvider#getCurrentState()
	 */
	public Map getCurrentState() {
		Map m = new HashMap();
		m.put(ICountService.COUNT, Integer.valueOf(count));
		return m;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.ISourceProvider#getProvidedSourceNames()
	 */
	public String[] getProvidedSourceNames() {
		return PROVIDED_SOURCE_NAMES;
	}

}
