/*
 * Copyright 2007 tamacat.org
 * Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0
 */
package cloud.tamacat.di.impl;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import cloud.tamacat.di.DIContainerException;
import cloud.tamacat.di.define.BeanDefine;
import cloud.tamacat.di.define.BeanDefineMap;
import cloud.tamacat.di.test.Core;
import cloud.tamacat.di.test.DBCore;
import cloud.tamacat.di.test.Param;
import cloud.tamacat.di.test.SampleCore;
import cloud.tamacat.util.ClassUtils;
import cloud.tamacat.util.ResourceNotFoundException;


public class DefaultDIContainerTest {

	ClassLoader loader;
	DefaultDIContainer di;

	@BeforeEach
	protected void setUp() {
		loader = ClassUtils.getDefaultClassLoader();//new UnloadableClassLoader();
	}

	@Test
	public void testDIContainerConstructorException() {
		try {
			di = new DefaultDIContainer("not_found.xml", loader);
			fail(di.toString() + " is not fail.");
		} catch (Exception e) {
			//e.printStackTrace();
			assertEquals(ResourceNotFoundException.class, e.getClass());
			assertTrue(true);
		}
	}

	@Test
	public void testDIContainerConstructorMap() {
		BeanDefineMap defines = new BeanDefineMap();
		BeanDefine def = new BeanDefine();
		def.setId("test");
		def.setType("cloud.tamacat.di.test.SampleCore", loader);
		def.setSingleton(false);
		defines.put(def.getId(), def);

		di = new DefaultDIContainer(defines, loader);
		Object o = di.getBean("test");
		assertEquals(o.getClass(), ClassUtils.forName("cloud.tamacat.di.test.SampleCore", loader));
	}

	@Test
	public void testGetBeanSingleton() {
		di = new DefaultDIContainer("test.xml", loader);
		Core core = (Core) di.getBean("Core");
		assertNotNull(core);
		//assertTrue(core instanceof Core);
		Core core2 = (Core) di.getBean("Core");
		assertTrue(core2 != core);

		Core core3 = (Core) di.getBean("Core-singleton");
		assertNotNull(core3);
		//assertTrue(core instanceof Core);
		Core core4 = (Core) di.getBean("Core-singleton");
		assertEquals(core3, core4);

		Core core5 = di.getBean("Core", Core.class);
		assertNotNull(core5);
	}

	@Test
	public void testGetBeanScopeSingleton() {
		di = new DefaultDIContainer("test.xml", loader);

		Core core1 = di.getBean("Core-scope-singleton", Core.class);
		assertNotNull(core1);
		//assertTrue(core1 instanceof Core);
		Core core2 = di.getBean("Core-scope-singleton", Core.class);
		assertEquals(core1, core2);
	}

	@Test
	public void testGetBeanPrototype() {
		di = new DefaultDIContainer("test.xml", loader);
		Core core = di.getBean("Core2", Core.class);
		assertNotNull(core);
		//assertTrue(core instanceof Core);

		Core core2 = di.getBean("Core2", Core.class);
		assertNotSame(core2, core);
	}

	@Test
	public void testGetBeanScopePrototype() {
		di = new DefaultDIContainer("test.xml", loader);
		Core core = di.getBean("Core-scope-prototype", Core.class);
		assertNotNull(core);
		//assertTrue(core instanceof Core);

		Core core2 = di.getBean("Core-scope-prototype", Core.class);
		assertNotSame(core2, core);
	}

	@Test
	public void testGetBeanRef() {
		di = new DefaultDIContainer("test.xml", loader);
		SampleCore core = di.getBean("Core3", SampleCore.class);
		assertNotNull(core);
		//assertTrue(core instanceof Core);

		assertEquals("CoreName", core.getCoreName());
		assertNotNull(core.getCore());
	}

	@Test
	public void testGetBeanParamTestSetterBasicType() {
		di = new DefaultDIContainer("test.xml", loader);
		Param t = di.getBean("ParamTestSetterBasicType", Param.class);
		assertNotNull(t);
		//assertTrue(t instanceof Param);

		assertEquals("Test", t.getStringValue());
		assertEquals(100, t.getIntValue());
		assertEquals(1234567890000L, t.getLongValue());
		assertEquals(123.456f, t.getFloatValue());
		assertEquals(123456789.123456789d, t.getDoubleValue());
		assertEquals('a', t.getCharValue());
		assertEquals(true, t.isBooleanValue());
		t.setVoid();
	}

	@Test
	public void testGetBeanParamTestConstructorArgString() {
		di = new DefaultDIContainer("test.xml", loader);
		Param t = (Param) di.getBean("ParamTestConstructorArgsString");
		assertNotNull(t);
		//assertTrue(t instanceof Param);

		assertEquals("Test", t.getStringValue());
	}

	@Test
	public void testGetBeanParamTestConstructorMultiArgsStringAutoTypes() {
		di = new DefaultDIContainer("test.xml", loader);
		Param t = (Param) di.getBean("ParamTestConstructorMultiArgsStringAutoTypes");
		assertNotNull(t);
		//assertTrue(t instanceof Param);

		assertEquals("Test1", t.getStringValue());
		assertEquals("Test2", t.getStringValue2());
	}

	@Test
	public void testGetBeanParamTestConstructorMultiArgsStringFixedTypes() {
		di = new DefaultDIContainer("test.xml", loader);
		Param t = (Param) di.getBean("ParamTestConstructorMultiArgsStringFixedTypes");
		assertNotNull(t);
		//assertTrue(t instanceof Param);

		assertEquals("Test1", t.getStringValue());
		assertEquals("Test2", t.getStringValue2());
	}

	@Test
	public void testGetBeanParamTestConstructorArgsRef() {
		di = new DefaultDIContainer("test.xml", loader);
		Param t = (Param) di.getBean("ParamTestConstructorArgsRef");
		assertNotNull(t);
		//assertTrue(t instanceof Param);
		assertEquals("Test", t.getParamTest().getStringValue());
	}

	@Test
	public void testGetBeanParamTestConstructorMultiArgsRefAutoTypes() {
		di = new DefaultDIContainer("test.xml", loader);
		Param t = (Param) di.getBean("ParamTestConstructorMultiArgsRefAutoTypes");
		assertNotNull(t);
		//assertTrue(t instanceof Param);

		assertEquals("Test", t.getParamTest().getStringValue());
		assertEquals("Test", t.getParam2().getStringValue());
	}

	@Test
	public void testGetBeanParamTestConstructorMultiArgsRefFixedTypes() {
		di = new DefaultDIContainer("test.xml", loader);
		Param t = (Param) di.getBean("ParamTestConstructorMultiArgsRefFixedTypes");
		assertNotNull(t);
		//assertTrue(t instanceof Param);

		assertEquals("Test", t.getParamTest().getStringValue());
		assertEquals("Test", t.getParam2().getStringValue());
	}

	@Test
	public void testGetBeanParamTestConstructorArgsNull() {
		di = new DefaultDIContainer("test.xml", loader);
		Param t = (Param) di.getBean("ParamTestConstructorArgsNull");
		assertNotNull(t);
		//assertTrue(t instanceof Param);

		assertEquals(null, t.getStringValue());
	}

	@Test
	public void testGetBeanParamTestSetterNull() {
		di = new DefaultDIContainer("test.xml", loader);
		Param t = (Param) di.getBean("ParamTestSetterNull");
		assertNotNull(t);
		//assertTrue(t instanceof Param);

		assertEquals(null, t.getStringValue());
	}

	@Test
	public void testGetBeanParamTestSetterEmpty() {
		di = new DefaultDIContainer("test.xml", loader);
		Param t = (Param) di.getBean("ParamTestSetterEmpty");
		assertNotNull(t);
		//assertTrue(t instanceof Param);

		assertEquals("", t.getStringValue());
	}

	@Test
	public void testGetBeanParamTestInitMethod() {
		di = new DefaultDIContainer("test.xml", loader);
		Param t = (Param) di.getBean("ParamTestInitMethod");
		assertNotNull(t);
		//assertTrue(t instanceof Param);

		assertEquals("Initialized", t.getInitValue());

		t = (Param) di.getBean("ParamTestSetterNull");
		assertEquals(null, t.getInitValue());
	}

	@Test
	public void testGetInstanceOfType() {
		di = new DefaultDIContainer("test.xml", loader);

		List<?> list = di.getInstanceOfType(Core.class);
		for (Object o : list) {
			assertEquals(true, o instanceof Core);
		}

		List<?> list2 = di.getInstanceOfType(DBCore.class);
		for (Object o : list2) {
			assertEquals(true, o instanceof DBCore);
			((DBCore)o).setCoreName("DBCore");
			assertEquals("DBCore", ((DBCore)o).getCoreName());
		}

		List<?> list3 = di.getInstanceOfType(null);
		assertTrue(0 == list3.size());
	}

	@Test
	public void testParamTestCDATA() {
		di = new DefaultDIContainer("test.xml", loader);
		Param param = (Param) di.getBean("ParamTestCDATA");
		assertEquals("<html>TEST</html>", param.getStringValue().trim());
	}

	@Test
	public void testFactoryMethodSingleton() {
		di = new DefaultDIContainer("test.xml", loader);
		Object o1 = di.getBean("FactoryMethodSingleton");
		assertTrue(o1 instanceof Core);
		assertTrue(o1.getClass() == DBCore.class);

		Object o2 = di.getBean("FactoryMethodSingleton");
		assertEquals(o1, o2);
	}

	@Test
	public void testFactoryMethodNotSingleton() {
		di = new DefaultDIContainer("test.xml", loader);
		Object o1 = di.getBean("FactoryMethodNotSingleton");
		assertTrue(o1 instanceof Core);
		assertTrue(o1.getClass() == DBCore.class);

		Object o2 = di.getBean("FactoryMethodNotSingleton");
		assertNotSame(o1, o2);
	}

	@Test
	public void testSingleAliases() {
		di = new DefaultDIContainer("test.xml", loader);
		Param param = (Param)di.getBean("AliasTest");
		assertEquals("Test", param.getStringValue());

		Param org = (Param) di.getBean("AliasTestSingle");
		assertEquals(org.getStringValue(), param.getStringValue());
	}

	@Test
	public void testMultiAliases() {
		di = new DefaultDIContainer("test.xml", loader);
		Param param1 = (Param)di.getBean("AliasTest1");
		assertEquals("Test", param1.getStringValue());

		Param param2 = (Param)di.getBean("AliasTest2");
		assertEquals("Test", param2.getStringValue());
	}

	@Test
	public void testRemoveBean() {
		BeanDefineMap defines = new BeanDefineMap();
		BeanDefine def = new BeanDefine();
		def.setId("test");
		def.setType("cloud.tamacat.di.test.SampleCore", loader);
		def.setSingleton(false);
		defines.put(def.getId(), def);

		di = new DefaultDIContainer(defines, loader);
		Object o = di.getBean("test");
		assertEquals(o.getClass(), ClassUtils.forName("cloud.tamacat.di.test.SampleCore", loader));

		di.removeBean("test");
		try {
			di.getBean("test");
			fail();
		} catch (DIContainerException e) {
			assertNotNull(e);
		}
	}

	@Test
	public void testRemoveBeansType() {
		BeanDefineMap defines = new BeanDefineMap();
		BeanDefine def = new BeanDefine();
		def.setId("test");
		def.setType("cloud.tamacat.di.test.SampleCore", loader);
		def.setSingleton(false);
		defines.put(def.getId(), def);

		di = new DefaultDIContainer(defines, loader);
		Object o = di.getBean("test");
		assertEquals(o.getClass(), ClassUtils.forName("cloud.tamacat.di.test.SampleCore", loader));

		di.removeBeans(Core.class);
		Object o2 = di.getBean("test");
		assertNotNull(o2);

		di.removeBeans(SampleCore.class);
		try {
			di.getBean("test");
			fail();
		} catch (DIContainerException e) {
			assertNotNull(e);
		}
	}
}
