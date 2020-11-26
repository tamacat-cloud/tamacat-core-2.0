/*
 * Copyright 2014 tamacat.org
 * Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0
 */
package cloud.tamacat.di.define;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import cloud.tamacat.util.ClassUtils;

public class BeanDefineParamTest {

	@BeforeEach
	public void setUp() throws Exception {
	}

	@Test
	public void testSetNameString() {
		BeanDefineParam param = new BeanDefineParam();
		param.setName("interceptor");
		assertEquals("setInterceptor", param.getName());
	}

	@Test
	public void testSetNameStringString() {
		BeanDefineParam param = new BeanDefineParam();
		param.setName("interceptor","add");
		assertEquals("addInterceptor", param.getName());

		param.setName("interceptor","");
		assertEquals("Interceptor", param.getName());

		param.setName("interceptor", null);
		assertEquals("setInterceptor", param.getName());
	}

	@Test
	public void testReturnType() {
		BeanDefineParam param = new BeanDefineParam();
		param.setReturnType(String.class);
		assertEquals(String.class, param.getReturnType());
	}

	@Test
	public void testIsRegistMethod() {
		BeanDefineParam param = new BeanDefineParam();
		assertFalse(param.isRegistMethod());

		param.setMethod(ClassUtils.getMethod(String.class, "toString"));
		assertTrue(param.isRegistMethod());
	}

	@Test
	public void testClone() throws Exception {
		BeanDefineParam param = new BeanDefineParam();
		param.setName("value");
		BeanDefineParam clone = param.clone();
		assertEquals("setValue", clone.getName());
	}
}
