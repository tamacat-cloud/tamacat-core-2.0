/*
 * Copyright 2007 tamacat.org
 * Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0
 */
package cloud.tamacat.log.impl;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class JDKLoggerTest {

    JDKLogger logger;

    @BeforeEach
    public void setUp() throws Exception {
		System.setProperty("java.util.logging.config.file", "logging.properties");

        logger = new JDKLogger("org.tamacat.log.JDKLoggerTest");
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
    public void testErrorString() {
        logger.error("test");
    }
    
    @Test
    public void testDebugStringStringArray() {
        logger.debug("arg0=${0}, arg1=${1}", "one", "two");
    }

    @Test
    public void testInfoStringStringArray() {
        logger.info("arg0=${0}, arg1=${1}", "one", "two");
    }
    
    @Test
    public void testIsEnabled() {
        assertTrue(logger.isFatalEnabled());
        assertTrue(logger.isErrorEnabled());
        assertTrue(logger.isWarnEnabled());
        assertTrue(logger.isInfoEnabled());
        assertFalse(logger.isDebugEnabled());
        assertFalse(logger.isTraceEnabled());
    }
}
