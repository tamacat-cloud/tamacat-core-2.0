/*
 * Copyright 2014 tamacat.org
 * Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0
 */
package cloud.tamacat.di.define;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BeanConstructorParamTest {

	@BeforeEach
	public void setUp() throws Exception {
	}

	@Test
	public void testRefId() {
		BeanConstructorParam param = new BeanConstructorParam();
		param.setRefId("test");
		assertEquals("test", param.getRefId());
	}

	@Test
	public void testIsRef() {
		BeanConstructorParam param = new BeanConstructorParam();
		assertFalse(param.isRef());

		param.setRefId("test");
		assertTrue(param.isRef());
	}

	@Test
	public void testType() {
		BeanConstructorParam param = new BeanConstructorParam();
		param.setType("cloud.tamacat.di.test.SampleCore");

		assertEquals("cloud.tamacat.di.test.SampleCore", param.getType());
	}

	@Test
	public void testGetValue() {
		BeanConstructorParam param = new BeanConstructorParam();
		param.setValue("test");

		assertEquals("test", param.getValue());
	}

	@Test
	public void testClone() throws Exception {
		BeanConstructorParam param = new BeanConstructorParam();
		param.setType("java.lang.Integer");
		param.setValue("123");

		BeanConstructorParam clone = param.clone();
		assertEquals("123", clone.getValue());
		assertEquals("java.lang.Integer", clone.getType());
	}

}
