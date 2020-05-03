/*
 * Copyright (c) 2007 tamacat.org
 */
package cloud.tamacat.di.define;

import java.util.Map.Entry;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import cloud.tamacat.di.define.BeanCreator;
import cloud.tamacat.di.define.BeanDefine;
import cloud.tamacat.di.define.BeanDefineMap;
import cloud.tamacat.di.test.Core;
import cloud.tamacat.di.test.SampleCore;

public class BeanCreatorTest {
	
	BeanDefineMap defines = new BeanDefineMap();

	@BeforeEach
	public void setUp() throws Exception {
		BeanDefine def = new BeanDefine();
		def.setId("test");
		def.setType(SampleCore.class);
		defines.add(def);
	}

	@Test
	public void testGetBeanSample() {
		BeanCreator creator = new BeanCreator(defines);
		Core core = creator.getBean("test", Core.class);
		assertTrue(core instanceof SampleCore);
	}

//	@Test
//	public void debug() {
//		BeanCreator creator = new BeanCreator(defines);
//		for (Entry<String, BeanDefine> def : creator.getDefines().entrySet()) {
//			BeanDefine bean = def.getValue();
//			printBean(bean);
//		}
//	}
//
//	void printBean(BeanDefine bean ) {
//		trace("class=" + bean.getType().getName());
//		trace("  id=" + bean.getId() + " singleton=" + bean.isSingleton());
//		List<BeanConstructorParam> cpList = bean.getConstructorArgs();
//		for (BeanConstructorParam p : cpList) {
//			trace("    constructor-arg ref=" + p.getRefId());
//			trace("    constructor-arg value=" + p.getValue());
//			trace("    constructor-arg type=" + p.getType());
//		}
//		List<BeanDefineParam> pList = bean.getPropertyList();
//		for (BeanDefineParam p : pList) {
//			if (p.isRef()) {
//				trace("    setter ref=" + p.getRefId());
//			} else {
//				trace("    setter name=" + p.getName() + " value=" + p.getValue());
//			}
//		}
//	}

	@Test
	public void testGetInstanceOfType() {
		BeanCreator creator = new BeanCreator(defines);
		for (Entry<String, BeanDefine> entry : creator.getDefines().entrySet()) {
			trace(entry.getKey() + "=" + entry.getValue());
		}
	}

	@Test
	public void testRemoveBean() {
		BeanCreator creator = new BeanCreator(defines);

		creator.removeBean("");
	}

	@Test
	public void testRemoveBeans() {
		BeanCreator creator = new BeanCreator(defines);
		creator.removeBeans(String.class);
	}

	void trace(String m) {
		System.out.println(m.toString());
	}

}
