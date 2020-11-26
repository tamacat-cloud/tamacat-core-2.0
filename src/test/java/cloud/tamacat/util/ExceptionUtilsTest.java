/*
 * Copyright 2009 tamacat.org
 * Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0
 */
package cloud.tamacat.util;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class ExceptionUtilsTest {

	@Test
	public void testExceptionUtuls() {
		new ExceptionUtils();
	}
	
	@Test
	public void testGetStackTrace() {
		Exception e = new Exception("test");
		String trace = ExceptionUtils.getStackTrace(e);
		assertTrue(trace.startsWith("java.lang.Exception: test"));
	}

	@Test
	public void testGetStackTraceThrowableInt() {
		Exception e = new Exception("test");
		String trace = ExceptionUtils.getStackTrace(e, 25);
		assertEquals("java.lang.Exception: test...", trace);
	}
	
	@Test
	public void testIsRuntime() {
		assertFalse(ExceptionUtils.isRuntime(new Exception("test")));
		assertTrue(ExceptionUtils.isRuntime(new RuntimeException("test")));
	}

	@Test
	public void testGetCauseException() {
		assertNull(ExceptionUtils.getCauseException(null));

		Exception e = new Exception("test");
		assertEquals(e, ExceptionUtils.getCauseException(e));
		
		Exception cause = new Exception("cause");
		e = new Exception(cause);
		assertEquals(cause, ExceptionUtils.getCauseException(e));
	}
}
