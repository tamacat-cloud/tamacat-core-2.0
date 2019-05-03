/*
 * Copyright (c) 2007 tamacat.org
 */
package cloud.tamacat;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import cloud.tamacat.Version;

public class VersionTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testToString() {
		String version = new Version().toString();
		assertEquals("2.0", version);
	}
}
