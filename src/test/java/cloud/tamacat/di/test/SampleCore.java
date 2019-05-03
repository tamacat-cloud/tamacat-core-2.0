/*
 * Copyright (c) 2007 tamacat.org
 */
package cloud.tamacat.di.test;

public class SampleCore implements Core {

	private String coreName = "Test";

	private Core core;

	public String getCoreName() {
		return coreName;
	}

	public void setCoreName(String coreName) {
		this.coreName = coreName;
	}

	public Core getCore() {
		return core;
	}

	public void setCore(Core core) {
		this.core = core;
	}

	private String name;
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
