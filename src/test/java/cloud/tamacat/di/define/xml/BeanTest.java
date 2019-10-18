/*
 * Copyright (c) 2014 tamacat.org
 */
package cloud.tamacat.di.define.xml;

import static org.junit.Assert.*;

import java.io.File;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXB;

import org.junit.Before;
import org.junit.Test;

import cloud.tamacat.di.define.xml.Bean;
import cloud.tamacat.di.define.xml.ConstructorArg;
import cloud.tamacat.di.define.xml.Prop;

public class BeanTest {

	@Before
	public void setUp() throws Exception {

	}

	@Test
	public void testMarshal() {
		Bean bean = new Bean();
		bean.setId("test");
		bean.setClassName("cloud.tamacat.test.Data");

		StringWriter writer = new StringWriter();
		JAXB.marshal(bean, writer);
		String xml = writer.toString();
		System.out.println(xml);
		assertEquals("<?xml version=1.0 encoding=UTF-8 standalone=yes?>"
			+ "<bean id=test class=cloud.tamacat.test.Data/>", xml.replace("\n","").replace("\"", ""));
	}

	@Test
	public void testUnmarshal() {
		String xml = "src/test/resources/test/BeanTest.xml";
		Bean bean = JAXB.unmarshal(new File(xml), Bean.class);
		assertEquals("test", bean.getId());
		assertEquals("cloud.tamacat.test.Data", bean.getClassName());
	}

	@Test
	public void testName() {
		Bean bean = new Bean();
		bean.setName("test");
		assertEquals("test", bean.getName());
	}

	@Test
	public void testScope() {
		Bean bean = new Bean();
		bean.setScope("singleton");
		assertEquals("singleton", bean.getScope());
	}

	@Test
	public void testConstructorArg() {
		Bean bean = new Bean();
		ConstructorArg arg1 = new ConstructorArg();
		arg1.setValue("test");

		ConstructorArg arg2 = new ConstructorArg();
		arg2.setValue("123");
		arg2.setType("java.lang.Integer");

		List<ConstructorArg> args = new ArrayList<>();
		args.add(arg1);
		args.add(arg2);

		bean.setConstructorArgs(args);
		assertNotNull(bean.getConstructorArgs());
		assertEquals(arg1, bean.getConstructorArgs().get(0));
		assertEquals(arg2, bean.getConstructorArgs().get(1));
	}

	@Test
	public void testAddConstructorArg() {
		Bean bean = new Bean();
		ConstructorArg arg1 = new ConstructorArg();
		arg1.setValue("test");

		ConstructorArg arg2 = new ConstructorArg();
		arg2.setValue("123");
		arg2.setType("java.lang.Integer");

		bean.addConstructorArg(arg1);
		bean.addConstructorArg(arg2);

		assertNotNull(bean.getConstructorArgs());
		assertEquals(arg1, bean.getConstructorArgs().get(0));
		assertEquals(arg2, bean.getConstructorArgs().get(1));
	}

	@Test
	public void testFactoryMethod() {
		Bean bean = new Bean();
		bean.setFactoryMethod("getInstance");
		assertEquals("getInstance", bean.getFactoryMethod());
	}

	@Test
	public void testInitMethod() {
		Bean bean = new Bean();
		bean.setInitMethod("init");
		assertEquals("init", bean.getInitMethod());
	}

	@Test
	public void testProps() {
		Bean bean = new Bean();
		Prop prop1 = new Prop();
		prop1.setName("name1");
		prop1.setValue("value1");

		Prop prop2 = new Prop();
		prop2.setName("number");
		prop2.setValue("123");

		List<Prop> props = new ArrayList<>();
		props.add(prop1);
		props.add(prop2);
		bean.setProperties(props);

		assertNotNull(bean.getConstructorArgs());
		assertEquals(prop1, bean.getProperties().get(0));
		assertEquals(prop2, bean.getProperties().get(1));
	}

	@Test
	public void testAddProperty() {
		Bean bean = new Bean();
		Prop prop1 = new Prop();
		prop1.setName("name1");
		prop1.setValue("value1");

		Prop prop2 = new Prop();
		prop2.setName("number");
		prop2.setValue("123");

		bean.addProperty(prop1);
		bean.addProperty(prop2);

		assertNotNull(bean.getConstructorArgs());
		assertEquals(prop1, bean.getProperties().get(0));
		assertEquals(prop2, bean.getProperties().get(1));
	}
}
