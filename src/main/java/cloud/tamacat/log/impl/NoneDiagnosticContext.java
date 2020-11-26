/*
 * Copyright 2008 tamacat.org
 * Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0
 */
package cloud.tamacat.log.impl;

import cloud.tamacat.log.DiagnosticContext;

public class NoneDiagnosticContext implements DiagnosticContext {

	public void remove() {
	}

	public void remove(String key) {
	}

	public void setMappedContext(String key, String data) {
	}

	public void setNestedContext(String data) {
	}
}
