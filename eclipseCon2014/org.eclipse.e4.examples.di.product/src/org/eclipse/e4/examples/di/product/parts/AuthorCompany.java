/*******************************************************************************
 * Copyright (c) 2014 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.e4.examples.di.product.parts;

class AuthorCompany {
	public String name;
	public String company;

	public AuthorCompany(String n, String c) {
		name = n;
		company = c;
	}

	@Override
	public boolean equals(Object o) {
		if (o instanceof AuthorCompany) {
			return ((AuthorCompany) o).name.equals(name)
					&& ((AuthorCompany) o).company.equals(company);
		}
		return false;
	}

	@Override
	public int hashCode() {
		return name.hashCode() * 87 + company.hashCode();
	}
}