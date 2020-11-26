/*
 * Copyright 2019 tamacat.org
 * Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0
 */
package cloud.tamacat.util;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class MessageDigestExceptionTest {

	@Test
	public void testMessageDigestException() {
		MessageDigestException e = new MessageDigestException();
		assertEquals(null, e.getMessage());
		assertEquals(null, e.getCause());
	}

	@Test
	public void testMessageDigestExceptionString() {
		MessageDigestException e = new MessageDigestException("Test Message");
		assertEquals("Test Message", e.getMessage());
		assertEquals(null, e.getCause());
	}

	@Test
	public void testMessageDigestExceptionThrowable() {
		Exception cause = new Exception("Test Message");
		MessageDigestException e = new MessageDigestException(cause);
		assertEquals("java.lang.Exception: Test Message", e.getMessage());
        assertEquals("Test Message", e.getCause().getMessage());
    }

	@Test
	public void testMessageDigestExceptionStringThrowable() {
		Exception cause = new Exception("Test Message1");
		MessageDigestException e = new MessageDigestException("Test Message2", cause);
        assertEquals("Test Message2", e.getMessage());
        assertEquals("Test Message1", e.getCause().getMessage());
    }

}
