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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.handlers.HandlerUtil;
import org.eclipse.ui.internal.util.Util;

/**
 * @since 1.0
 *
 */
public class CatHandler extends AbstractHandler {

	/* (non-Javadoc)
	 * @see org.eclipse.core.commands.IHandler#execute(org.eclipse.core.commands.ExecutionEvent)
	 */
	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		ISelection selection = HandlerUtil.getActiveMenuSelection(event);
		if (!(selection instanceof IStructuredSelection)) {
			selection = HandlerUtil.getCurrentSelection(event);
			if (!(selection instanceof IStructuredSelection)) {
				return null;
			}
		}
		IStructuredSelection ssel = (IStructuredSelection) selection;
		Iterator i = ssel.iterator();
		while (i.hasNext()) {
			IResource resource = (IResource) Util.getAdapter(i.next(), IResource.class);
			if (resource instanceof IFile) {
				IFile file = (IFile) resource;
				try {
					BufferedReader reader = new BufferedReader(new InputStreamReader(file.getContents()));
					String line = null;
					while ((line=reader.readLine())!=null) {
						System.out.println(line);
					}
				} catch (CoreException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}

}
