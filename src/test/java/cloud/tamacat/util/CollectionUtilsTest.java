/*
 * Copyright 2020 tamacat.org
 * Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0
 */
package cloud.tamacat.util;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;

import org.junit.jupiter.api.Test;

public class CollectionUtilsTest {

	@Test
	public void testConvert() {		
		Map<String,String> converted = CollectionUtils.convert(
				new HashMap<String,String>(),
				Arrays.asList("name1", "name2"), 
				Arrays.asList("value1", "value2")
		);
		assertEquals("value1", converted.get("name1"));
		assertEquals("value2", converted.get("name2"));
		
		try {
			CollectionUtils.convert(
					new HashMap<String,String>(),
					Arrays.asList("name1", "name2"), 
					Arrays.asList("value1", "value2","value2")
			);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Invalid Collection. Collection size is not equals.", e.getMessage());
		}
	}

	@Test
	public void testNewArrayList() {
		assertEquals(ArrayList.class, CollectionUtils.newArrayList().getClass());
	}

	@Test
	public void testNewHashSet() {
		assertEquals(HashSet.class, CollectionUtils.newHashSet().getClass());
	}

	@Test
	public void testNewLinkedHashSet() {
		assertEquals(LinkedHashSet.class, CollectionUtils.newLinkedHashSet().getClass());
	}

	@Test
	public void testNewHashMap() {
		assertEquals(HashMap.class, CollectionUtils.newHashMap().getClass());
	}

	@Test
	public void testNewLinkedHashMap() {
		assertEquals(LinkedHashMap.class, CollectionUtils.newLinkedHashMap().getClass());
	}

}
