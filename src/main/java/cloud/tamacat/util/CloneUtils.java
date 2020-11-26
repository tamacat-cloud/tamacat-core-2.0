/*
 * Copyright 2011 tamacat.org
 * Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0
 */
package cloud.tamacat.util;

import java.lang.reflect.Method;

public class CloneUtils {

	public static <T> T clone(T obj) throws CloneNotSupportedException {
		if (obj == null) {
			return null;
		}
		if (obj instanceof Cloneable) {
			Class<?> type = obj.getClass();
			Method method = ClassUtils.searchMethod(type, "clone", (Class[]) null);
			if (method == null) {
				throw new NoSuchMethodError("No such clone method");
			}
			@SuppressWarnings("unchecked")
			T clone = (T) ClassUtils.invoke(method, obj, (Object[]) null);
			if (clone != null) {
				return clone;
			}
		}
		throw new CloneNotSupportedException();
	}
}
