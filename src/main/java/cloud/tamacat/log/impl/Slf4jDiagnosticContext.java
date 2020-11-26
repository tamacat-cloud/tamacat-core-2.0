/*
 * Copyright 2018 tamacat.org
 * Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0
 */
package cloud.tamacat.log.impl;

import org.slf4j.MDC;
import cloud.tamacat.log.DiagnosticContext;

public class Slf4jDiagnosticContext implements DiagnosticContext {

    @Override
    public void setNestedContext(String data) {
        MDC.put(Thread.currentThread().getName(), data);
    }

    @Override
    public void setMappedContext(String key, String data) {
        MDC.put(key, data);
    }

    @Override
    public void remove() {
        MDC.clear();
    }

    @Override
    public void remove(String key) {
        MDC.remove(key);
    }
}