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

import jakarta.xml.bind.JAXB;

public class RefTest {

	@BeforeEach
	public void setUp() throws Exception {

	}

	@Test
	public void testMarshal() {
		Ref ref = new Ref();
		ref.setBean("test");

		StringWriter writer = new StringWriter();
		JAXB.marshal(ref, writer);
		String xml = writer.toString();
		//System.out.println(xml);
		assertEquals("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n"
			+ "<ref bean=\"test\"/>\n", xml);
	}

	@Test
	public void testUnmarshal() {
		String xml = "src/test/resources/test/RefTest.xml";
		Ref ref = JAXB.unmarshal(new File(xml), Ref.class);
		assertEquals("test", ref.getBean());
	}

}
