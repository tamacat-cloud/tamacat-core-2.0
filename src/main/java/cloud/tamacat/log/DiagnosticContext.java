/*
 * Copyright 2008 tamacat.org
 * Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0
 */
package cloud.tamacat.log;

/**
 * <p>The interface of diagnostic context for logging.
 * 
 * <ul>
 *   <li>Nested diagnostic context.(NDC)</li>
 *   <li>Mapped diagnostic context.(MDC)</li>
 * </ul>
 */
public interface DiagnosticContext {

	void setNestedContext(String data);
	
	void setMappedContext(String key, String data);
	
	void remove();
	
	void remove(String key);
}
