/*
 * Copyright 2020 tamacat.org
 * Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0
 */
package cloud.tamacat.util;

import static org.junit.jupiter.api.Assertions.*;

import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;

import org.junit.jupiter.api.Test;

public class JsonUtilsTest {

	@Test
	public void testFromJsonInClasspath() {
		HashMap<?,?> test = JsonUtils.fromJsonInClasspath("test.json", HashMap.class);
		assertEquals("value1", test.get("name1"));
		assertEquals("value2", test.get("name2"));
	}

	@Test
	public void testFromJsonInputStreamClassOfT() {
		HashMap<?,?> test = JsonUtils.fromJson(IOUtils.getInputStream("test.json"), HashMap.class);
		assertEquals("value1", test.get("name1"));
		assertEquals("value2", test.get("name2"));
	}

	@Test
	public void testFromJsonReaderClassOfT() {
		HashMap<?,?> test = JsonUtils.fromJson(
				new InputStreamReader(IOUtils.getInputStream("test.json"),StandardCharsets.UTF_8),
				HashMap.class
			);
		assertEquals("value1", test.get("name1"));
		assertEquals("value2", test.get("name2"));
	}

	@Test
	public void testFromJsonStringClassOfT() {
		String json = "{\"name2\":\"value2\",\"name1\":\"value1\"}";
		
		HashMap<?,?> test = JsonUtils.fromJson(json, HashMap.class);
		assertEquals("value1", test.get("name1"));
		assertEquals("value2", test.get("name2"));
	}

	@Test
	public void testToJson() {
		HashMap<String,String> test = new HashMap<>();
		test.put("name1", "value1");
		test.put("name2", "value2");
		
		assertEquals("{\"name2\":\"value2\",\"name1\":\"value1\"}", JsonUtils.toJson(test));
	}

	@Test
	public void testStringifyObject() {
		HashMap<String,String> test = new HashMap<>();
		test.put("name1", "value1");
		test.put("name2", "value2");
		
		String json = JsonUtils.stringify(test);
		assertTrue(json.contains("\n"));
		assertTrue(json.contains("  \"name2\": \"value2\""));
		assertTrue(json.contains("  \"name1\": \"value1\""));
	}

	@Test
	public void testFormat() {
		String data = "{\"name2\":\"value2\",\"name1\":\"value1\"}";
		
		String json = JsonUtils.format(data);
		assertTrue(json.contains("\n"));
		assertTrue(json.contains("  \"name2\":\"value2\""));
		assertTrue(json.contains("  \"name1\":\"value1\""));
	}

}
