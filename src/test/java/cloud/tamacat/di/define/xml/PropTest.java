/*
 * Copyright 2014 tamacat.org
 * Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0
 */
package cloud.tamacat.di.define.xml;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.StringWriter;

import javax.xml.bind.JAXB;

public class PropTest {

	@BeforeEach
	public void setUp() throws Exception {

	}

	@Test
	public void testMode() {
		Prop prop = new Prop();
		prop.setName("name");
		prop.setValue("Test");
		prop.setMode("add");
		assertEquals("add", prop.getMode());
	}

	@Test
	public void testMarshal() {
		Prop prop = new Prop();
		prop.setName("name");
		prop.setValue("Test");

		StringWriter writer = new StringWriter();
		JAXB.marshal(prop, writer);
		String xml = writer.toString();
		//System.out.println(xml);
		assertEquals("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n"
			+ "<property name=\"name\">\n"
			+ "    <value>Test</value>\n"
			+ "</property>\n", xml);
	}

	@Test
	public void testUnmarshal() {
		String xml = "src/test/resources/test/PropTest.xml";
		Prop prop = JAXB.unmarshal(new File(xml), Prop.class);
		assertEquals("name", prop.getName());
		assertEquals("Test", prop.getValue());
		assertEquals(null, prop.getRef());
	}

}
