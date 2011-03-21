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

package org.eclipse.example.e4.commands;

import org.eclipse.jface.viewers.LabelProvider;

class VersionLabelProvider extends LabelProvider {
	@Override
	public String getText(Object element) {
		if (element instanceof Version) {
			return ((Version)element).getVersion();
		}
		return super.getText(element);
	}
}