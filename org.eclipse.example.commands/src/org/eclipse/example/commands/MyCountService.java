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

import org.eclipse.ui.ISourceProvider;

/**
 * @since 1.0
 * 
 */
public class MyCountService implements ICountService {
	private MyCountProvider provider;

	public MyCountService(ISourceProvider sp) {
		provider = (MyCountProvider) sp;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.example.commands.ICountService#getCount()
	 */
	public int getCount() {
		return provider.getCount();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.example.commands.ICountService#incrementCount()
	 */
	public int incrementCount() {
		provider.increment();
		return getCount();
	}

}
