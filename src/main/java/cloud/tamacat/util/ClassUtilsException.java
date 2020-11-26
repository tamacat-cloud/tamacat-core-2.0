/*
 * Copyright 2007 tamacat.org
 * Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0
 */
package cloud.tamacat.util;

public class ClassUtilsException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ClassUtilsException(String arg0) {
		super(arg0);
	}

	public ClassUtilsException(Throwable arg0) {
		super(arg0);
	}

	public ClassUtilsException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

}
