/*
 * Copyright 2018 tamacat.org
 * Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0
 */
package cloud.tamacat.log.impl;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import cloud.tamacat.log.LogFactory;

public class Slf4jLoggerTest {

    Slf4jLogger logger;

    @BeforeEach
    public void setUp() throws Exception {
        logger = new Slf4jLogger("TEST");
    }

    @Test
    public void testLogLevelStringStringArray() {
        logger.trace("arg0=${0}, arg1=${1}", "one", "two");
    }

    @Test
    public void testDebugString() {
        logger.debug("test");
    }

    @Test
    public void testDebugStringStringArray() {
        logger.debug("arg0=${0}, arg1=${1}", "one", "two");
    }

    @Test
    public void testIsEnabled() {
        assertTrue(logger.isFatalEnabled());
        assertTrue(logger.isErrorEnabled());
        assertTrue(logger.isWarnEnabled());
        assertTrue(logger.isInfoEnabled());
        //assertTrue(logger.isDebugEnabled());
        //assertTrue(logger.isTraceEnabled());
    }
    
    @Test
    public void testNDC() {
    	LogFactory.getDiagnosticContext(logger).setNestedContext("admin");
        logger.debug("Test DiagnosticContext");
    	LogFactory.getDiagnosticContext(logger).remove();
        logger.debug("Test DiagnosticContext2");
    }
    
    public void testGetOriginalLogger() {
    	Object original = logger.getOriginalLogger();
    	assertNotNull(original);
    	//assertTrue(original instanceof org.apache.log4j.Logger);
    }
}
