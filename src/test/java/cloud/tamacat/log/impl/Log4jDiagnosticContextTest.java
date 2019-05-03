package cloud.tamacat.log.impl;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import cloud.tamacat.log.impl.Log4jDiagnosticContext;

public class Log4jDiagnosticContextTest {

	Log4jDiagnosticContext context;
	
	@Before
	public void setUp() throws Exception {		
		context = new Log4jDiagnosticContext();
	}

	@After
	public void tearDown() throws Exception {
		//context.remove();
	}

	@Test
	public void testSetMappedContext() {
		context = new Log4jDiagnosticContext();
		context.setMappedContext("key1", "value1");
		context.setMappedContext("key2", "value2");
		//assertEquals(2, context.keySet().size());
	}

	@Test
	public void testSetNestedContext() {
		context.setNestedContext("value1");
		context.setNestedContext("value2");
		assertEquals("value1 value2", context.get());
	}

	@Test
	public void testRemove() {
		context.setNestedContext("value1");
		context.setNestedContext("value2");
		context.remove();
		assertEquals(null, context.get());
	}

	@Test
	public void testRemoveString() {
		context.setMappedContext("key1", "value1");
		context.setMappedContext("key2", "value2");
		//assertEquals(2, context.keySet().size());
		
		context.remove("key1");
		//assertEquals(1, context.keySet().size());
		context.remove("key2");
		assertEquals(0, context.keySet().size());
	}
}
