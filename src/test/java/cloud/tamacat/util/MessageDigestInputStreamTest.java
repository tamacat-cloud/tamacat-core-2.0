package cloud.tamacat.util;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.security.MessageDigest;

import cloud.tamacat.util.IOUtils;
import cloud.tamacat.util.MessageDigestException;
import cloud.tamacat.util.MessageDigestInputStream;

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
		assertSame(2L, result);

		assertSame(0L, in.skip(-999));
		IOUtils.close(in);
	}

	@Test
	public void testMessageDigestInputStreamInputStream() {
		ByteArrayInputStream bi = new ByteArrayInputStream("TEST".getBytes());
		MessageDigestInputStream in = new MessageDigestInputStream(bi);
		try {
			assertEquals("d41d8cd98f00b204e9800998ecf8427e", in.getDigest());
		} finally {
			IOUtils.close(in);
			IOUtils.close(bi);
		}
	}

	@Test
	public void testMessageDigestInputStreamInputStreamString() {
		ByteArrayInputStream bi = new ByteArrayInputStream("TEST".getBytes());
		MessageDigestInputStream in = new MessageDigestInputStream(bi, "SHA");
		assertEquals("da39a3ee5e6b4b0d3255bfef95601890afd80709", in.getDigest());

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
		MessageDigest digest = MessageDigest.getInstance("SHA");
		in.setMessageDigest(digest);
		assertEquals("da39a3ee5e6b4b0d3255bfef95601890afd80709", in.getDigest());
		IOUtils.close(in);
	}
}
