/*
 * Copyright 2019 tamacat.org
 * Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0
 */
package cloud.tamacat.util;

public class MessageDigestException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public MessageDigestException() {
	}

	public MessageDigestException(String message) {
		super(message);
	}

	public MessageDigestException(Throwable cause) {
		super(cause);
	}

	public MessageDigestException(String message, Throwable cause) {
		super(message, cause);
	}
}
