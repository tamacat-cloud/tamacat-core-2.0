/*
 * Copyright 2007 tamacat.org
 * Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0
 */
package cloud.tamacat;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
