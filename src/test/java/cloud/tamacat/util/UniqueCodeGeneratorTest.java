/*
 * Copyright (c) 2008, tamacat.org
 * All rights reserved.
 */
package cloud.tamacat.util;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.HashSet;

import cloud.tamacat.util.ClassUtils;
import cloud.tamacat.util.UniqueCodeGenerator;

public class UniqueCodeGeneratorTest {

	@BeforeEach
	public void setUp() throws Exception {
		System.setProperty("user.dir", new File(ClassUtils.getURL(".").getPath()).getAbsolutePath());
	}

	@AfterEach
	public void tearDown() throws Exception {
	}

	@Test
	public void testGenerate() {
		for (int j=0; j<10; j++) {
			HashSet<String> uniq = new HashSet<>(1000);
			for (int i=0; i<1000; i++) {
				String uuid = UniqueCodeGenerator.generate();
				if (uniq.contains(uuid) == false) {
					uniq.add(uuid);
					//System.out.println(uuid);
				} else {
					fail();
				}
			}
		}
	}
	
	@Test
	public void testGenerateDelimiter() {
		assertTrue(UniqueCodeGenerator.generate(false).indexOf("-")>=0);
		assertTrue(UniqueCodeGenerator.generate(true).indexOf("-")==-1);
	}

	@Test
	public void testGenerateString() {
		assertTrue(UniqueCodeGenerator.generate("test").startsWith("test"));

		assertFalse(UniqueCodeGenerator.generate(null).startsWith("test"));
	}
}
