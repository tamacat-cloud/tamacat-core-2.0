/*
 * Copyright 2014 tamacat.org
 * Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0
 */
package cloud.tamacat.di.impl;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import cloud.tamacat.di.test.CoreFactory;
import cloud.tamacat.di.test.SampleCore;
import cloud.tamacat.di.test.Singleton;

public class DIContainerTest {

	@Test
	public void testDI() {
		DefaultDIContainer di = new DefaultDIContainer("test0.xml");
		SampleCore t = di.getBean("Core3", SampleCore.class);
		//System.out.println(t.getCore());

		assertNotNull(t);
		assertTrue(t instanceof SampleCore);
		assertEquals("CoreName", t.getCoreName());
		t.setName("test");
		assertEquals("test", t.getName());
	}

	@Test
	public void testSingleton() {
		DefaultDIContainer di = new DefaultDIContainer("test0.xml");
		Singleton t = di.getBean("Singleton", Singleton.class);
		assertNotNull(t);
		assertEquals(new Singleton().getInstance(), t.getInstance());
	}


	@Test
	public void testFactory() {
		DefaultDIContainer di = new DefaultDIContainer("test0.xml");
		CoreFactory t = di.getBean("Factory", CoreFactory.class);
		assertNotNull(t);
	}
}
