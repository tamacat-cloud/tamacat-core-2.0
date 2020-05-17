package cloud.tamacat.log.impl;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class Log4j2DiagnosticContextTest {

	Log4j2DiagnosticContext context;
	
	@BeforeEach
	public void setUp() throws Exception {		
		context = new Log4j2DiagnosticContext();
	}

	@AfterEach
	public void tearDown() throws Exception {
		//context.remove();
	}

	@Test
	public void testSetMappedContext() {
		context = new Log4j2DiagnosticContext();
		context.setMappedContext("key1", "value1");
		context.setMappedContext("key2", "value2");
		//assertEquals(2, context.keySet().size());
	}

	@Test
	public void testSetNestedContext() {
		context.setNestedContext("value1");
		context.setNestedContext("value2");
		assertEquals("value2", context.peek());
	}

	@Test
	public void testRemove() {
		context.setNestedContext("value1");
		context.setNestedContext("value2");
		context.remove();
		assertEquals("", context.peek());
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
