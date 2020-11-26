/*
 * Copyright 2014 tamacat.org
 * Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0
 */
package cloud.tamacat.di.define.xml;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.StringWriter;

import javax.xml.bind.JAXB;

public class JAXBTest {

	@Test
	public void marshal() {
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
		JAXB.marshal(beans, writer);
		String xml = writer.toString();
		System.out.println(xml);
	}

	@Test
	public void unmarshal() throws Exception {
		File file = new File("src/test/resources/test.xml");
		Beans beans = JAXB.unmarshal(file, Beans.class);
		System.out.println(beans.getBeans());
	}
}
