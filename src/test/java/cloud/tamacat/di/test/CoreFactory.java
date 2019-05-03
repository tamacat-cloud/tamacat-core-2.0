/*
 * Copyright (c) 2007 tamacat.org
 */
package cloud.tamacat.di.test;

public class CoreFactory {

	public static Core createCore() {
		return new DBCore();
	}
}
