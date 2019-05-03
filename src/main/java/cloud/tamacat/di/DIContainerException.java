/*
 * Copyright (c) 2007 tamacat.org
 */
package cloud.tamacat.di;

public class DIContainerException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public DIContainerException(String message) {
		super(message);
	}

	public DIContainerException(Throwable cause) {
		super(cause);
	}

	public DIContainerException(String message, Throwable cause) {
		super(message, cause);
	}
}
