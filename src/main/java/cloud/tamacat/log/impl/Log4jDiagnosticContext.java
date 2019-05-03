/*
 * Copyright (c) 2008 tamacat.org
 * All rights reserved.
 */
package cloud.tamacat.log.impl;

import java.util.Hashtable;
import java.util.Set;

import org.apache.log4j.MDC;
import org.apache.log4j.NDC;

import cloud.tamacat.log.DiagnosticContext;

public class Log4jDiagnosticContext implements DiagnosticContext {

	@Override
	public void setMappedContext(String key, String data) {
		MDC.put(key, data);
	}

	@Override
	public void setNestedContext(String data) {
		NDC.push(data);
	}

	@Override
	public void remove() {
		NDC.remove();
		MDC.clear();
	}
	
	@Override
	public void remove(String key) {
		MDC.remove(key);
	}

	public String get() {
		return NDC.get();
	}
	
	protected Set<?> keySet() {
		Hashtable<?, ?> context = MDC.getContext();
		return context != null ? context.keySet() : new Hashtable<>().keySet();
	}
}
