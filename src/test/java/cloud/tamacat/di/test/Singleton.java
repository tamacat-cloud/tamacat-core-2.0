/*
 * Copyright (c) 2007 tamacat.org
 */
package cloud.tamacat.di.test;

public class Singleton {

	static final Singleton SELF = new Singleton();

	public Singleton getInstance() {
		return SELF;
	}
}
