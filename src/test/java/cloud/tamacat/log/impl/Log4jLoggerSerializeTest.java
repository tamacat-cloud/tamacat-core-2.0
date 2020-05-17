/*
 * Copyright (c) 2009, tamacat.org
 * All rights reserved.
 */
package cloud.tamacat.log.impl;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import cloud.tamacat.util.IOUtils;

public class Log4jLoggerSerializeTest {
	
	@Test
	public void testSerialize() {
		ObjectOutputStream out = null;
		ObjectInputStream in = null;
		try {
			Log4j2Logger logger = new Log4j2Logger("TEST");
			
			//FileOutputStream os = new FileOutputStream("logger.ser");
			ByteArrayOutputStream os = new ByteArrayOutputStream();
			out = new ObjectOutputStream(os);
			out.writeObject(logger);
			byte[] serialize = os.toByteArray();
			assertNotNull(serialize);
			//System.out.println(new String(serialize));
			logger.debug("Serialize OK. length=" + serialize.length);
			
			//FileInputStream is = new FileInputStream("logger.ser");
			ByteArrayInputStream is = new ByteArrayInputStream(serialize);
			in = new ObjectInputStream(is);
			Object obj = in.readObject();
			assertNotNull(obj);
			assertEquals(Log4j2Logger.class, obj.getClass());
			
			logger = (Log4j2Logger)obj;
			logger.debug("Deserialize OK.");
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		} finally {
			IOUtils.close(in);
			IOUtils.close(out);
		}
	}
}
