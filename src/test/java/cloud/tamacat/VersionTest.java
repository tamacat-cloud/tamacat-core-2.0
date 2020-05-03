/*
 * Copyright (c) 2007 tamacat.org
 */
package cloud.tamacat;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import cloud.tamacat.Version;

public class VersionTest {

	@BeforeEach
	public void setUp() throws Exception {
	}

	@Test
	public void testToString() {
		String version = new Version().toString();
		assertEquals("2.0", version);
	}
}
