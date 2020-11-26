/*
 * Copyright 2008 tamacat.org
 * Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 
 */
package cloud.tamacat.di.define;

public interface StringValueConverter<T> {

	Class<T> getType();

	T convert(String param);
}
