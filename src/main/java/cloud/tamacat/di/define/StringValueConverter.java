/*
 * Copyright (c) 2008 tamacat.org
 */
package cloud.tamacat.di.define;

public interface StringValueConverter<T> {

	Class<T> getType();

	T convert(String param);
}
