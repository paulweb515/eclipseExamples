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
package org.eclipse.e4.examples.di.extensions.internal;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import org.eclipse.e4.core.di.suppliers.ExtendedObjectSupplier;
import org.eclipse.e4.core.di.suppliers.IObjectDescriptor;
import org.eclipse.e4.core.di.suppliers.IRequestor;

public class ExtensionsObjectSupplier extends ExtendedObjectSupplier {
	// TODO get the IExtensionRegistry

	@Override
	public Object get(IObjectDescriptor descriptor, IRequestor requestor,
			boolean track, boolean group) {

		final Class<?> desiredType = getDesiredClass(descriptor
				.getDesiredType());
		// We will pass through a list
		if (List.class.isAssignableFrom(desiredType)) {
			// TODO Fill in the List
			// TODO track it if requested
		}

		// Annotation used with unsupported type
		return null;
	}

	// TODO provide a registry listener that can work with the extension point
	// and IRequestor
	// TODO we support added(IExtension[]) and removed(IExtension[])

	private Class<?> getDesiredClass(Type desiredType) {
		if (desiredType instanceof Class<?>)
			return (Class<?>) desiredType;
		if (desiredType instanceof ParameterizedType) {
			Type rawType = ((ParameterizedType) desiredType).getRawType();
			if (rawType instanceof Class<?>)
				return (Class<?>) rawType;
		}
		return null;
	}
}
