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
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.xml.bind.JAXB;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

public class BeansTest {

	@BeforeEach
	public void setUp() throws Exception {

	}

	@Test
	public void testMarshal() throws Exception {
		Beans beans = new Beans();

		Bean ref = new Bean();
		ref.setId("data");
		ref.setClassName("cloud.tamacat.hoge.Data");
		beans.addBean(ref);

		Bean bean = new Bean();
		bean.setId("test");
		bean.setClassName("cloud.tamacat.hoge.Fuga");

		bean.addProperty("aaa", "bbb");

		bean.addProperty("data", ref);

		beans.addBean(bean);

		StringWriter writer = new StringWriter();
		JAXBContext jc = JAXBContext.newInstance(Beans.class);
		Marshaller marshaller = jc.createMarshaller();
		marshaller.setProperty( Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.FALSE);
		marshaller.marshal(beans, writer);
		String xml = writer.toString();
		String org = new String(Files.readAllBytes(Paths.get("src/test/resources/test/BeansTest.xml")),"UTF-8");
		System.out.println(xml);

		assertEquals(org.replace("\t","").replace(" ","").replace("\r","").replace("\n",""),
			xml.replace("\t","").replace(" ",""));
	}

	@Test
	public void testUnmarshal() {
		String xml = "src/test/resources/test/BeansTest.xml";
		Beans beans = JAXB.unmarshal(new File(xml), Beans.class);
		assertNotNull(beans.getBeans());
	}

}
