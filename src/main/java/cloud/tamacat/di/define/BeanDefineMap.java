/*
 * Copyright 2009 tamacat.org
 * Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 
 */
package cloud.tamacat.di.define;

import java.util.LinkedHashMap;

public class BeanDefineMap extends LinkedHashMap<String, BeanDefine> {
	private static final long serialVersionUID = 1L;

	public void add(BeanDefine define) {
		String id = define.getId();
		if (id != null) super.put(id, define);
	}
}
