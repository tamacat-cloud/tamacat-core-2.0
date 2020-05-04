/*
 * Copyright 2007 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package cloud.tamacat.log.impl;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import cloud.tamacat.log.impl.SimpleLogger;

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
