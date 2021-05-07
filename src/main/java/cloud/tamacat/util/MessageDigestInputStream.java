/*
 * Copyright 2019 tamacat.org
 * Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0
 */
package cloud.tamacat.util;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * FilterInputStream for MessageDigest. 
 * default algorithm: SHA-256
 */
public class MessageDigestInputStream extends FilterInputStream {

	private String algorithm = "SHA-256";
	private MessageDigest digest;

	public MessageDigestInputStream(InputStream in) {
		super(in);
		try {
			digest = MessageDigest.getInstance(algorithm);
		} catch (NoSuchAlgorithmException e) {
			throw new MessageDigestException(e);
		}
	}
	
	public MessageDigestInputStream(InputStream in, String algorithm) {
		super(in);
		try {
			digest = MessageDigest.getInstance(algorithm);
		} catch (NoSuchAlgorithmException e) {
			throw new MessageDigestException(e);
		}
	}
	
	/**
	 * Set the Message Digest Algorithm.
	 * default: SHA-256
	 * @param algorithm
	 */
	public void setAlgorithm(String algorithm) {
		this.algorithm = algorithm;
	}
	
	/**
	 * Set the Instance of MessageDigest.
	 * ex) MessageDigest.getInstance("SHA-512")
	 * @param digest
	 */
	public void setMessageDigest(MessageDigest digest) {
		this.digest = digest;
	}

	@Override
	public int read() throws IOException {
		int b = in.read();
		if (b != -1) {
			digest.update((byte) b);
		}
		return b;
	}

	@Override
	public int read(byte[] buf, int off, int len) throws IOException {
		len = in.read(buf, off, len);
		if (len != -1) {
			digest.update(buf, off, len);
		}
		return len;
	}

	@Override
	public long skip(long n) throws IOException {
		byte[] buf = new byte[512];
		long total = 0;
		while (total < n) {
			long len = n - total;
			len = read(buf, 0, len < buf.length ? (int) len : buf.length);
			if (len == -1) {
				return total;
			}
			total += len;
		}
		return total;
	}

	public String getDigest() {
		byte[] hash = digest.digest();
		BigInteger bigInt = new BigInteger(1, hash);
		return bigInt.toString(16);
	}
}
