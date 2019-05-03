/*
 * Copyright (c) 2014 tamacat.org
 */
package cloud.tamacat.di.define.xml;

import javax.xml.bind.JAXB;

import cloud.tamacat.di.define.BeanConstructorParam;
import cloud.tamacat.di.define.BeanDefine;
import cloud.tamacat.di.define.BeanDefineMap;
import cloud.tamacat.di.define.BeanDefineParam;
import cloud.tamacat.util.IOUtils;
import cloud.tamacat.util.StringUtils;

public class XmlBeanDefineCreator {

	public XmlBeanDefineCreator() {
	}

	public BeanDefineMap load(String xml, ClassLoader loader) {
		Beans beans = JAXB.unmarshal(IOUtils.getInputStream(xml, loader), Beans.class);
		BeanDefineMap defines = new BeanDefineMap();
		for (Bean bean : beans.getBeans()) {
			BeanDefine def = new BeanDefine();
			def.setId(bean.getId());
			def.setType(bean.getClassName(), loader);
			def.setAliases(bean.getName());
			def.setInitMethod(bean.getInitMethod());
			def.setFactoryMethod(bean.getFactoryMethod());
			def.setSingleton("singleton".equalsIgnoreCase(bean.getScope()));
			for (ConstructorArg arg : bean.getConstructorArgs()) {
				BeanConstructorParam param = new BeanConstructorParam();
				String type = arg.getType();
				if (StringUtils.isNotEmpty(type)) {
					param.setType(type);
				}
				String value = arg.getValue();
				if (value != null) {
					param.setValue(value);
				} else if (arg.getRefId() != null) {
					param.setRefId(arg.getRefId());
				}
				def.addConstructorArgs(param);
			}
			for (Prop prop : bean.getProperties()) {
				BeanDefineParam param = new BeanDefineParam();
				param.setName(prop.getName(), prop.getMode());
				if (prop.getValue() != null) {
					param.setValue(prop.getValue());
				} else if (prop.getRefId() != null) {
					param.setRefId(prop.getRefId());
				}
				def.addProperty(param);
			}
			defines.add(def);
		}
		return defines;
	}
}
