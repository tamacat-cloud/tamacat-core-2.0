/*
 * Copyright 2009 tamacat.org
 * Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 
 */
package cloud.tamacat.di;

import cloud.tamacat.di.define.BeanDefine;
import cloud.tamacat.di.define.BeanDefineMap;
import cloud.tamacat.di.impl.DefaultDIContainer;
import cloud.tamacat.util.ClassUtils;

/**
 * DI is creates {@link DIContainer}s from {@link BeanDefine}s
 * or configuration file(XML).
 */
public final class DI {

	/**
	 * Creates an {@link DIContainer} for the given set of configuration file(XML).
	 * @param defines Configuration file(XML) in CLASSPATH.
	 * @return {@link DIContainer}
	 */
	public static DIContainer configure(String xml) {
		return new DefaultDIContainer(xml);
	}

	/**
	 * Creates an {@link DIContainer} for the given set of configuration file(XML).
	 * @param defines Configuration file(XML) in CLASSPATH.
	 * @param loader instance of ClassLoader
	 * @return {@link DIContainer}
	 */
	public static DIContainer configure(String xml, ClassLoader loader) {
		return new DefaultDIContainer(xml,loader);
	}

	/**
	 * Creates an {@link DIContainer} for the given set of defines.
	 * @param defines Array of {@link BeanDefine}.
	 * @return {@link DIContainer}
	 */
	public static DIContainer configure(BeanDefine... defines) {
		BeanDefineMap defineMap = new BeanDefineMap();
		for (BeanDefine def : defines) {
			defineMap.add(def);
		}
		return new DefaultDIContainer(defineMap, ClassUtils.getDefaultClassLoader());
	}

	/**
	 * Creates an {@link DIContainer} for the given set of defines.
	 * @param defines BeanDefineMap, such as Map of {@link BeanDefine}.
	 * @param loader instance of ClassLoader
	 * @return {@link DIContainer}
	 */
	public static DIContainer configure(BeanDefineMap defines, ClassLoader loader) {
		return new DefaultDIContainer(defines, loader);
	}

	/**
	 * Creates an {@link DIContainer} for the given set of defines.
	 * @param defines BeanDefineMap, such as Map of {@link BeanDefine}.
	 * @return {@link DIContainer}
	 */
	public static DIContainer configure(BeanDefineMap defines) {
		return configure(defines, ClassUtils.getDefaultClassLoader());
	}

	static final DI DI = new DI();

	private DI() {}
}
