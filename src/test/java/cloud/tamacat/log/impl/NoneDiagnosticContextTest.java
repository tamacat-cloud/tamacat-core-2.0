/*
 * Copyright 2008 tamacat.org
 * Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0
 */
package cloud.tamacat.log.impl;

import org.junit.jupiter.api.Test;

public class NoneDiagnosticContextTest {

	@Test
	public void testRemove() {
		NoneDiagnosticContext ctx = new NoneDiagnosticContext();
		ctx.remove();
	}

	@Test
	public void testRemoveString() {
		NoneDiagnosticContext ctx = new NoneDiagnosticContext();
		ctx.remove("key1");
	}

	@Test
	public void testSetMappedContext() {
		NoneDiagnosticContext ctx = new NoneDiagnosticContext();
		ctx.setMappedContext("key1", "value1");
		ctx.setMappedContext("key2", "value2");
	}

	@Test
	public void testSetNestedContext() {
		NoneDiagnosticContext ctx = new NoneDiagnosticContext();
		ctx.setNestedContext("test");
	}

}
