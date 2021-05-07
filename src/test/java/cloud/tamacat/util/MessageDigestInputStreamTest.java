/*
 * Copyright 2019 tamacat.org
 * Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0
 */
package cloud.tamacat.util;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.security.MessageDigest;

public class MessageDigestInputStreamTest {

	ByteArrayInputStream bi;

	@BeforeEach
	public void setUp() throws Exception {
		bi = new ByteArrayInputStream("TEST".getBytes());
	}

	@AfterEach
	public void tearDown() throws Exception {
		IOUtils.close(bi);
	}

	@Test
	public void testRead() throws IOException {
		MessageDigestInputStream in = new MessageDigestInputStream(bi);
		int result = in.read();
		assertEquals(84, result);
		IOUtils.close(in);
	}

	@Test
	public void testReadByteArrayIntInt() throws IOException {
		ByteArrayInputStream bi = new ByteArrayInputStream("TEST".getBytes());
		MessageDigestInputStream in = new MessageDigestInputStream(bi);
		byte[] buf = new byte[256];
		int off = 0;
		int len = "TEST".length();
		int result = in.read(buf, off, len);
		assertEquals(4, result);
		IOUtils.close(in);
	}

	@Test
	public void testSkip() throws IOException {
		ByteArrayInputStream bi = new ByteArrayInputStream("TEST".getBytes());
		MessageDigestInputStream in = new MessageDigestInputStream(bi);
		long result = in.skip(2);
		assertEquals(2L, result);

		assertEquals(0L, in.skip(-999));
		IOUtils.close(in);
	}

	@Test
	public void testMessageDigestInputStreamInputStream() {
		ByteArrayInputStream bi = new ByteArrayInputStream("TEST".getBytes());
		MessageDigestInputStream in = new MessageDigestInputStream(bi);
		try {
			assertEquals("e3b0c44298fc1c149afbf4c8996fb92427ae41e4649b934ca495991b7852b855", in.getDigest());
		} finally {
			IOUtils.close(in);
			IOUtils.close(bi);
		}
	}

	@Test
	public void testMessageDigestInputStreamInputStreamString() {
		ByteArrayInputStream bi = new ByteArrayInputStream("TEST".getBytes());
		MessageDigestInputStream in = new MessageDigestInputStream(bi, "SHA-512");
		assertEquals("cf83e1357eefb8bdf1542850d66d8007d620e4050b5715dc83f4a921d36ce9ce47d0d13c5d85f2b0ff8318d2877eec2f63b931bd47417a81a538327af927da3e", in.getDigest());

		try {
			IOUtils.close(in);
		
			in = new MessageDigestInputStream(bi, "XXXX");
			fail();
		} catch (MessageDigestException e) {
			assertNotNull(e.getMessage());
		}
		IOUtils.close(in);
	}

	@Test
	public void testSetMessageDigestAndGetDigest() throws Exception {
		ByteArrayInputStream bi = new ByteArrayInputStream("TEST".getBytes());
		MessageDigestInputStream in = new MessageDigestInputStream(bi);
		MessageDigest digest = MessageDigest.getInstance("SHA-512");
		in.setMessageDigest(digest);
		assertEquals("cf83e1357eefb8bdf1542850d66d8007d620e4050b5715dc83f4a921d36ce9ce47d0d13c5d85f2b0ff8318d2877eec2f63b931bd47417a81a538327af927da3e", in.getDigest());
		IOUtils.close(in);
	}
}
