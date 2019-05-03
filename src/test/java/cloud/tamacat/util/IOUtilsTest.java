/*
 * Copyright (c) 2009, tamacat.org
 * All rights reserved.
 */
package cloud.tamacat.util;

import static org.junit.Assert.*;
import org.junit.*;

import cloud.tamacat.util.IOUtils;

import java.io.Closeable;
import java.io.IOException;
import java.net.Socket;

public class IOUtilsTest {

	@Test
	public void testIOUtils() {
		assertNotNull(new IOUtils());
	}

	@Test
	public void testGetInputStream() {
		IOUtils.getInputStream("");
	}

	@Test
	public void testgetClassPathToResourcePath() {
		assertEquals(null, IOUtils.getClassPathToResourcePath(null));
		assertEquals("", IOUtils.getClassPathToResourcePath(""));
		assertEquals("/test", IOUtils.getClassPathToResourcePath("/test"));
		assertEquals("./test", IOUtils.getClassPathToResourcePath("./test"));
	}

	@Test
	public void testClose() {
		ExampleCloseable obj = new ExampleCloseable();
		assertFalse(obj.isClosed());
		IOUtils.close((Object)obj);
		assertTrue(obj.isClosed());
	}

	@Test
	public void testCloseObjectNull() {
		ExampleCloseable target = null;
		IOUtils.close((Object)target);

		IOUtils.close((Closeable)target);
	}

	@Test
	public void testCloseObjectCloseMethod() {
		ExampleNotCloseable target = new ExampleNotCloseable();
		IOUtils.close(target);
	}

	@Test
	public void testCloseObject() {
		String target = "Test";
		IOUtils.close(target);

		ExampleIOExceptionNotCloseable target2 = new ExampleIOExceptionNotCloseable();
		try {
			IOUtils.close((Object)target2);
			fail();		
		} catch (RuntimeIOException e) {
			//e.printStackTrace();
			assertNotNull(e.getMessage());
		}
	}

	@Test
	public void testCloseSocket() {
		Socket socket = new Socket();
		IOUtils.close(socket);

		IOUtils.close((Socket)null);
	}

	@Test
	public void testCloseCloseable() {
		ExampleCloseable target = new ExampleCloseable();
		IOUtils.close(target);

		ExampleIOExceptionCloseable target2 = new ExampleIOExceptionCloseable();
		IOUtils.close(target2);
	}

	static class ExampleCloseable implements Closeable {
		boolean closed;

		public void close() throws IOException {
			//System.out.println("ExampleCloseable#close()");
			closed = true;
		}

		public boolean isClosed() {
			return closed;
		}
	}

	static class ExampleIOExceptionCloseable implements Closeable {
		public void close() throws IOException {
			System.out.println("ExampleCloseable#close()");
			throw new IOException("IOException test");
		}
	}

	static class ExampleNotCloseable {
		public void close() throws IOException {
			System.out.println("ExampleNotCloseable#close()");
		}
	}

	static class ExampleIOExceptionNotCloseable {
		public void close() throws IOException {
			throw new IOException("IOException test");
		}
	}
}
