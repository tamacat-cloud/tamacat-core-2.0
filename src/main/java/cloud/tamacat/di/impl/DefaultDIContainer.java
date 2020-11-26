/*
 * Copyright 2007 tamacat.org
 * Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 
 */
package cloud.tamacat.di.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Map.Entry;

import cloud.tamacat.di.DIContainer;
import cloud.tamacat.di.define.BeanCreator;
import cloud.tamacat.di.define.BeanDefine;
import cloud.tamacat.di.define.BeanDefineMap;
import cloud.tamacat.di.define.xml.XmlBeanDefineCreator;
import cloud.tamacat.util.ClassUtils;

public class DefaultDIContainer implements DIContainer {

	static final String PROPERTIES_FILE = "cloud.tamacat.di.DIContainer.properties";
	static final String BEAN_DEFINE_HANDLER_KEY = "DIContainerBeanDefineHandler";

	protected BeanCreator creator;
	protected Properties props;

	public DefaultDIContainer(String xml) {
		init(xml, ClassUtils.getDefaultClassLoader());
	}

	public DefaultDIContainer(String xml, ClassLoader loader) {
		init(xml, loader);
	}

	public DefaultDIContainer(BeanDefineMap defines, ClassLoader loader) {
		init(defines, loader);
	}

	@Override
	public Object getBean(String id) {
		return creator.getBean(id, null);
	}

	@Override
	public <T> T getBean(String id, Class<T> type) {
		return creator.getBean(id, type);
	}

	@Override
	public void removeBean(String id) {
		creator.removeBean(id);
	}

	@Override
	public <T>void removeBeans(Class<T> type) {
		creator.removeBeans(type);
	}

	@Override
	public <T> List<T> getInstanceOfType(Class<T> type) {
		List<T> list = new ArrayList<T>();
		if (type == null) return list;
		for (Entry<String, BeanDefine> entry : creator.getDefines().entrySet()) {
			if (ClassUtils.isTypeOf(entry.getValue().getType(), type)) {
				list.add(getBean(entry.getValue().getId(), type));
			}
		}
		return list;
	}

	protected void init(String xml, ClassLoader loader) {
		this.creator = new BeanCreator(new XmlBeanDefineCreator().load(xml, loader));
	}

	protected void init(BeanDefineMap defines, ClassLoader loader) {
		this.creator = new BeanCreator(defines, loader);
	}
}
