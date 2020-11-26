/*
 * Copyright 2007 tamacat.org
 * Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0
 */
package cloud.tamacat.di.test;

public class CoreFactory {

	public static Core createCore() {
		return new DBCore();
	}
}
