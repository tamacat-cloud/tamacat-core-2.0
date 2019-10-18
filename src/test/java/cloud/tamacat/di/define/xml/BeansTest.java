/*
 * Copyright (c) 2014 tamacat.org
 */
package cloud.tamacat.di.define.xml;

import static org.junit.Assert.*;

import java.io.File;
import java.io.StringWriter;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.xml.bind.JAXB;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

import org.junit.Before;
import org.junit.Test;

import cloud.tamacat.di.define.xml.Bean;
import cloud.tamacat.di.define.xml.Beans;

public class BeansTest {

	@Before
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
