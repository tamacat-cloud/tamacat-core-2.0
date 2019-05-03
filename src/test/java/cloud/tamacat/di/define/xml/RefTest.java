/*
 * Copyright (c) 2014 tamacat.org
 */
package cloud.tamacat.di.define.xml;

import static org.junit.Assert.*;

import java.io.File;
import java.io.StringWriter;

import javax.xml.bind.JAXB;

import org.junit.Before;
import org.junit.Test;

import cloud.tamacat.di.define.xml.Ref;

public class RefTest {

	@Before
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
