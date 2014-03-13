package org.eclipse.e4.examples.di.extensions;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.inject.Qualifier;

import org.eclipse.core.runtime.IConfigurationElement;

/**
 * This will inject the current valid list of {@link IConfigurationElement} for
 * this extension point.
 */
@Qualifier
@Documented
@Target({ ElementType.FIELD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface Extension {
	String value(); // The extension point id
}
