/*
 * Copyright 2011 tamacat.org
 * Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0
 */
package cloud.tamacat.util;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

public class CloneUtilsTest {

	@Test
	public void testClone() throws CloneNotSupportedException {
		new CloneUtils();
		
		assertNull(CloneUtils.clone(null));
		try {
			assertNull(CloneUtils.clone(new Target()));
			fail();
		} catch (CloneNotSupportedException e) {
			//OK
		}
		try {
			assertNull(CloneUtils.clone(new TargetCloneable()));
			fail();
		} catch (NoSuchMethodError e) {
			//OK
		}
		HashMap<String, String> test = new HashMap<String, String>();
		test.put("key1", "value1");
		HashMap<String, String> clone = CloneUtils.clone(test);
		assertEquals(clone.get("key1"), test.get("key1"));
	}

	static class Target {
		String name;
	}
	
	static class TargetCloneable implements Cloneable {
		String name;
	}
}
