/*
 * Copyright (c) 2014 tamacat.org
 */
package cloud.tamacat.di.define.xml;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.StringWriter;

import javax.xml.bind.JAXB;

import cloud.tamacat.di.define.xml.ConstructorArg;
import cloud.tamacat.di.define.xml.Ref;

public class ConstructorArgTest {

	@BeforeEach
	public void setUp() throws Exception {

	}

	@Test
	public void testMarshal() {
		ConstructorArg arg = new ConstructorArg();
		arg.setType("java.lang.Integer");
		arg.setValue("123");

		StringWriter writer = new StringWriter();
		JAXB.marshal(arg, writer);
		String xml = writer.toString();
		//System.out.println(xml);
		assertEquals("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n"
			+ "<constructor-arg type=\"java.lang.Integer\">\n"
			+ "    <value>123</value>\n"
			+ "</constructor-arg>\n", xml);
	}

	@Test
	public void testUnmarshal() {
		String xml = "src/test/resources/test/ConstructorArgTest.xml";
		ConstructorArg arg = JAXB.unmarshal(new File(xml), ConstructorArg.class);
		assertEquals("java.lang.Integer", arg.getType());
		assertEquals("123", arg.getValue());

		assertEquals(null, arg.getRef());
		assertEquals(null, arg.getRefId());
	}

	@Test
	public void testRef() {
		ConstructorArg arg = new ConstructorArg();
		assertEquals(null, arg.getRef());
		assertEquals(null, arg.getRefId());

		Ref ref = new Ref();
		ref.setBean("test");
		arg.setRef(ref);
		assertEquals(ref, arg.getRef());
		assertEquals("test", arg.getRefId());
	}

}
