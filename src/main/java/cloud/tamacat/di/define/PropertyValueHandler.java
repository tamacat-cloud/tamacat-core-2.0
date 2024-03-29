/*
 * Copyright 2008 tamacat.org
 * Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0
 */
package cloud.tamacat.di.define;

import java.lang.reflect.Method;
import java.util.HashMap;

import cloud.tamacat.util.ClassUtils;
import cloud.tamacat.util.StringUtils;
import cloud.tamacat.util.SystemPropertyUtils;

public class PropertyValueHandler {

	static final HashMap<Class<?>, StringValueConverter<?>> CONVERTERS = new HashMap<>();

	public static final void register(Class<?> type, StringValueConverter<?> converter) {
		CONVERTERS.put(type, converter);
	}
	
	static {
		register(String.class, new StringConverter());
		register(String[].class, new StringArrayConverter());
		register(Integer.class, new IntegerConverter());
		register(Long.class, new LongConverter());
		register(Float.class, new FloatConverter());
		register(Double.class, new DoubleConverter());
		register(Character.class, new CharacterConverter());
		register(Boolean.class, new BooleanConverter());
		register(Class.class, new ClassConverter());
		register(Class[].class, new ClassArrayConverter());
		register(Object.class, new ObjectConverter());
	}

	public static final void setEnv(String key, String value) {
		System.setProperty(key, value);
	}
	
	static final class StringConverter implements StringValueConverter<String> {
		public Class<String> getType() {
			return String.class;
		}

		public String convert(String param) {
			//return param;
			return replaceEnvironmentVariable(param);
		}
	}

	static final class StringArrayConverter implements StringValueConverter<String[]> {
		public Class<String[]> getType() {
			return String[].class;
		}

		public String[] convert(String param) {
			return StringUtils.split(replaceEnvironmentVariable(param), ",");
		}
	}

	static final class IntegerConverter implements StringValueConverter<Integer> {
		public Class<Integer> getType() {
			return Integer.TYPE;
		}

		public Integer convert(String param) {
			return Integer.parseInt(replaceEnvironmentVariable(param));
		}
	}

	static final class LongConverter implements StringValueConverter<Long> {
		public Class<Long> getType() {
			return Long.TYPE;
		}

		public Long convert(String param) {
			return Long.parseLong(replaceEnvironmentVariable(param));
		}
	}

	static final class FloatConverter implements StringValueConverter<Float> {
		public Class<Float> getType() {
			return Float.TYPE;
		}

		public Float convert(String param) {
			return Float.parseFloat(replaceEnvironmentVariable(param));
		}
	}

	static final class DoubleConverter implements StringValueConverter<Double> {
		public Class<Double> getType() {
			return Double.TYPE;
		}

		public Double convert(String param) {
			return Double.parseDouble(replaceEnvironmentVariable(param));
		}
	}

	static final class CharacterConverter implements StringValueConverter<Character> {
		public Class<Character> getType() {
			return Character.TYPE;
		}

		public Character convert(String param) {
			return Character.valueOf(replaceEnvironmentVariable(param).charAt(0));
		}
	}

	static final class BooleanConverter implements StringValueConverter<Boolean> {
		public Class<Boolean> getType() {
			return Boolean.TYPE;
		}

		public Boolean convert(String param) {
			String value = replaceEnvironmentVariable(param);
			if ("true".equalsIgnoreCase(value)) {
				return Boolean.TRUE.booleanValue();
			} else if ("false".equalsIgnoreCase(value)) {
				return Boolean.FALSE.booleanValue();
			} else {
				throw new RuntimeException("can not convert boolean.");
			}
		}
	}

	@SuppressWarnings("rawtypes")
	static final class ClassConverter implements StringValueConverter<Class> {
		public Class<Class> getType() {
			return Class.class;
		}

		public Class<?> convert(String param) {
			return ClassUtils.forName(replaceEnvironmentVariable(param));
		}
	}

	@SuppressWarnings("rawtypes")
	static final class ClassArrayConverter implements StringValueConverter<Class[]> {
		public Class<Class[]> getType() {
			return Class[].class;
		}

		public Class<?>[] convert(String param) {
			return new Class[] { ClassUtils.forName(replaceEnvironmentVariable(param)) };
		}
	}

	static final class ObjectConverter implements StringValueConverter<Object> {
		public Class<Object> getType() {
			return Object.class;
		}

		public Object convert(String param) {
			return replaceEnvironmentVariable(param);
		}
	}

	private Class<?> type;
	private BeanDefineParam param;

	public PropertyValueHandler(Class<?> type, BeanDefineParam param) {
		this.type = type;
		this.param = param;
	}

	public <T> Object resolvParameterValue(T instance) {
		if (param.isRegistMethod()) {
			StringValueConverter<?> cv = CONVERTERS.get(param.getParamType());
			if (cv != null) {
				return ClassUtils.invoke(param.getMethod(), instance, cv.convert(param.getValue()));
			}
		}
		Method[] methods = ClassUtils.findMethods(type, param.getName());
		for (StringValueConverter<?> cv : CONVERTERS.values()) {
			try {
				Object value = cv.convert(param.getValue());
				Method m = ClassUtils.searchMethod(methods, param.getName(), cv.getType());
				if (m != null) { // success.
					@SuppressWarnings("unused")
					Object result = ClassUtils.invoke(m, instance, value);
					if (param.isRegistMethod() == false) {
						param.setStringValueConverter(cv);
						param.setMethod(m);
						param.setParamType(cv.getType());
					}
					return instance;
				}
			} catch (Exception e) {
			}
		}
		return instance;
	}
	
	/**
	 * Replace a environment variable from setter injection value.
	 * ex. ${USER_HOME} -> /home/testuser
	 * @param value
	 * @since 1.4-20180510
	 */
	public static String replaceEnvironmentVariable(String value) {
		return SystemPropertyUtils.resolvePlaceholders(value, true);
	}
	
	/**
	 * @deprecated
	 * @see SystemPropertyUtils.resolvePlaceholders(String)
	 * 
	 * Replace a environment variable from setter injection value.
	 * ex. ${USER_HOME} -> /home/testuser
	 * @param value
	 */
	public static String replaceEnvVariable(String value) {
		if (StringUtils.isNotEmpty(value) && value.startsWith("${") && value.endsWith("}")) {
			String key = value.substring(2, value.length()-1);
			String env = System.getenv(key);
			if (env != null) {
				return env;
			} else {
				return System.getProperty(key);
			}
		}
		return value;
	}
}
