/*
 * Copyright (c) 2014 tamacat.org
 */
package cloud.tamacat.di.define;

import java.lang.reflect.Method;

import junit.framework.TestCase;

import org.junit.Test;

import cloud.tamacat.di.define.BeanConstructorParam;
import cloud.tamacat.di.define.BeanDefine;
import cloud.tamacat.di.define.BeanDefineParam;
import cloud.tamacat.di.test.CoreFactory;
import cloud.tamacat.di.test.Param;
import cloud.tamacat.util.ClassUtils;

public class BeanDefineTest extends TestCase {

	@Test
	public void testSetType() {
		BeanDefine def = new BeanDefine();
		def.setType("java.lang.String", null);
		assertEquals(String.class, def.getType());

		def.setType("java.lang.String", ClassUtils.getDefaultClassLoader());
		assertEquals(String.class, def.getType());
	}

	@Test
	public void testGetAliases() {
		BeanDefine def = new BeanDefine();
		assertNotNull(def.getAliases());
		def.setAliases("test");
		assertEquals("test", def.getAliases()[0]);
	}

	@Test
	public void testInitMethod() {
		BeanDefine def = new BeanDefine();
		Method init = ClassUtils.getMethod(Param.class, "init", (Class<?>)null);
		def.setInitMethod(init);
		assertEquals(init, def.getInitMethod());
	}

	@Test
	public void testFactoryMethod() {
		BeanDefine def = new BeanDefine();
		Method factory = ClassUtils.getMethod(CoreFactory.class, "createCore", (Class<?>)null);
		def.setFactoryMethod(factory);
		assertEquals(factory, def.getFactoryMethod());
	}

	@Test
	public void testClone() throws Exception {
		//Setup BeanDefine.
		BeanDefine org = new BeanDefine();
		org.addConstructorArgs(new BeanConstructorParam());
		org.getPropertyList().add(new BeanDefineParam());
		org.setId("Test");
		org.setAliases("t");
		org.setType(Integer.class);
		org.setSingleton(false);

		//Execute cloning.
		BeanDefine clone = org.clone();

		//Test of instance is equals.
		assertEquals(org.getId(), clone.getId());
		assertEquals(org.getAliases()[0], clone.getAliases()[0]);
		assertEquals(org.isSingleton(), clone.isSingleton());
		assertEquals(org.getType(), clone.getType());

		//Test of instance is not same. (org != clone)
		assertNotSame(org, clone);
		assertNotSame(org.getConstructorArgs(), clone.getConstructorArgs());
		assertNotSame(org.getPropertyList(), clone.getPropertyList());
	}
}
