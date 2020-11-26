/*
 * Copyright 2007 tamacat.org
 * Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0
 */
package cloud.tamacat.log.impl;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SimpleLoggerTest {

    SimpleLogger logger;
	ByteArrayOutputStream out;

    @BeforeEach
    public void setUp() throws Exception {
    	out = new ByteArrayOutputStream();
        logger = new SimpleLogger(new PrintStream(out));
    }

    @AfterEach
    public void tearDown() throws Exception {
    	//System.out.print(out.toString(StandardCharsets.UTF_8));
    	if (out != null) out.close();
    }
    
    @Test
    public void testSimpleLoggerConstructor() {
        logger = new SimpleLogger();
        logger.info("test");
        assertTrue(true);
    }

    @Test
    public void testLogLevelString() {
        logger.info("test");
        assertTrue(true);
    }

    @Test
    public void testLogLevelStringStringArray() {
        logger.debug("arg0=${0}, arg1=${1}", "one", "two");
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
        logger.level = logger.getDebugLevel();
        assertTrue(logger.isFatalEnabled());
        assertTrue(logger.isErrorEnabled());
        assertTrue(logger.isWarnEnabled());
        assertTrue(logger.isInfoEnabled());
        assertTrue(logger.isDebugEnabled());
        assertFalse(logger.isTraceEnabled());
    }
    
    @Test
    public void testGetOriginalLogger() {
    	Object original = logger.getOriginalLogger();
    	assertNotNull(original);
    	assertTrue(original instanceof SimpleLogger);
    	assertSame(original, logger);
    }

    @Test
    public void testFatalString() {
        logger.fatal("test");
    }
    
    @Test
    public void testErrorString() {
        logger.error("test");
    }
    
    @Test
    public void testWarnString() {
        logger.warn("test");
    }
    
    @Test
    public void testFatalObjectString() {
        logger.fatal(Integer.MAX_VALUE, "test1", "test2");
    }
    
    @Test
    public void testErrorObjectString() {
        logger.error(Integer.MAX_VALUE, "test1", "test2");
    }
    
    @Test
    public void testWarnObjectString() {
        logger.warn(Integer.MAX_VALUE, "test1", "test2");
    }
    
    @Test
    public void testFatalObjectThrowable() {
        logger.fatal("test", new RuntimeException("message"));
    }
    
    @Test
    public void testErrorObjectThrowable() {
        logger.error("test", new RuntimeException("message"));
    }
    
    @Test
    public void testWarnObjectThrowable() {
        logger.warn("test", new RuntimeException("message"));
    }
}
