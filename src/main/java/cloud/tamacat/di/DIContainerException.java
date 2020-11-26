/*
 * Copyright 2007 tamacat.org
 * Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 
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
