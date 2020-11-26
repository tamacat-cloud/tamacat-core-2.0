/*
 * Copyright 2014 tamacat.org
 * Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0
 */
package cloud.tamacat.di.define;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class PropertyValueHandlerTest {

	@Test
	public void testReplaceEnvironmentVariable() {
		PropertyValueHandler.setEnv("LOCAL_SERVER", "localhost");
		assertEquals(
			"localhost", 
			PropertyValueHandler.replaceEnvironmentVariable("${LOCAL_SERVER}")
		);
	}
	
	@Test
	public void testStringConverter() {
		PropertyValueHandler.setEnv("LOCAL_SERVER", "localhost");
		assertEquals(
			"localhost", 
			new PropertyValueHandler.StringConverter().convert("${LOCAL_SERVER}")
		);
	}
	
	@Test
	public void testStringArrayConverter() {
		PropertyValueHandler.setEnv("LOCAL_SERVER", "localhost, example.com");
		assertEquals(
			"localhost", 
			new PropertyValueHandler.StringArrayConverter().convert("${LOCAL_SERVER}")[0]
		);
		assertEquals(
			"example.com", 
			new PropertyValueHandler.StringArrayConverter().convert("${LOCAL_SERVER}")[1]
		);
	}
	
	@Test
	public void testIntegerConverter() {
		PropertyValueHandler.setEnv("PORT", "8080");
		assertEquals(
			8080, 
			new PropertyValueHandler.IntegerConverter().convert("${PORT}").intValue()
		);
	}
	
	@Test
	public void testLongConverter() {
		PropertyValueHandler.setEnv("LIMIT", String.valueOf(Long.MAX_VALUE));
		assertEquals(
			Long.MAX_VALUE, 
			new PropertyValueHandler.LongConverter().convert("${LIMIT}").longValue()
		);
	}
	
	@Test
	public void testFloatConverter() {
		PropertyValueHandler.setEnv("LIMIT", String.valueOf(Float.MAX_VALUE));
		assertEquals(
			String.valueOf(Float.MAX_VALUE), 
			String.valueOf(new PropertyValueHandler.FloatConverter().convert("${LIMIT}").floatValue())
		);
	}
	
	@Test
	public void testDoubleConverter() {
		PropertyValueHandler.setEnv("LIMIT", String.valueOf(Double.MAX_VALUE));
		assertEquals(
			String.valueOf(Double.MAX_VALUE), 
			String.valueOf(new PropertyValueHandler.DoubleConverter().convert("${LIMIT}").doubleValue())
		);
	}
	
	@Test
	public void testBooleanConverter() {
		PropertyValueHandler.setEnv("TEST", "true");
		assertTrue(new PropertyValueHandler.BooleanConverter().convert("${TEST}").booleanValue());
		
		PropertyValueHandler.setEnv("TEST", "false");
		assertFalse(new PropertyValueHandler.BooleanConverter().convert("${TEST}").booleanValue());
	}
	
	@Test
	public void testClassConverter() {
		PropertyValueHandler.setEnv("CLASS", "java.lang.String");
		assertEquals(
			String.class, 
			new PropertyValueHandler.ClassConverter().convert("${CLASS}")
		);
	}
}
