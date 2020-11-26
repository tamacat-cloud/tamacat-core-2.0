/*
 * Copyright 2007 tamacat.org
 * Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 
 */
package cloud.tamacat.di.define;

import java.lang.reflect.Method;

import cloud.tamacat.util.ClassUtils;

public class BeanDefineParam implements Cloneable {

	private String refId;
	private String name;

	private Class<?> returnType;
	private Class<?> paramType;
	private String value;

	private Method method;
	private StringValueConverter<?> converter;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = ClassUtils.getSetterMethodName(name);
	}

	public void setName(String name, String mode) {
		String methodMode = "set";
		if (mode != null) {
			methodMode = mode;
		}
		this.name = methodMode + ClassUtils.getCamelCaseName(name);
	}

	public String getRefId() {
		return refId;
	}

	public void setRefId(String refId) {
		this.refId = refId;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public boolean isRef() {
		return refId != null;
	}

	public Method getMethod() {
		return method;
	}

	public void setMethod(Method method) {
		this.method = method;
	}

	public boolean isRegistMethod() {
		return (method != null);
	}

	public Class<?> getReturnType() {
		return returnType;
	}

	public void setReturnType(Class<?> returnType) {
		this.returnType = returnType;
	}

	public Class<?> getParamType() {
		return paramType;
	}

	public void setParamType(Class<?> paramType) {
		this.paramType = paramType;
	}

	public void setStringValueConverter(StringValueConverter<?> converter) {
		this.converter = converter;
	}

	public StringValueConverter<?> getStringValueConverter() {
		return converter;
	}

	@Override
	public BeanDefineParam clone() throws CloneNotSupportedException {
		return (BeanDefineParam) super.clone();
	}
}
