/*
 * Copyright (c) 2007 tamacat.org
 */
package cloud.tamacat.di.define;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import cloud.tamacat.di.define.BeanAdapter;

public class BeanAdapterTest {

	BeanAdapter<String> adapter;
	String instance = new String("tama");

	@BeforeEach
	public void setUp() {
		adapter = new BeanAdapter<String>("id", String.class, instance);
	}

	@Test
	public void testGetId() {
		assertEquals("id", adapter.getId());
	}

	@Test
	public void testGetInstance() {
		assertSame(instance, adapter.getInstance());
	}

	@Test
	public void testGetType() {
		assertEquals(String.class, adapter.getType());
	}
}
