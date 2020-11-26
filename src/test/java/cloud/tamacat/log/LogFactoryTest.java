/*
 * Copyright 2007 tamacat.org
 * Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0
 */
package cloud.tamacat.log;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import cloud.tamacat.log.impl.NoneDiagnosticContext;
import cloud.tamacat.log.impl.Slf4jDiagnosticContext;
import cloud.tamacat.log.impl.Slf4jLogger;
import cloud.tamacat.util.ClassUtils;

public class LogFactoryTest {

	Log logger;

	@BeforeEach
	public void setUp() throws Exception {
		logger = LogFactory.getLog(LogFactoryTest.class);
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
		assertTrue(logger.isDebugEnabled());
		assertFalse(logger.isTraceEnabled());
	}

	@Test
	public void testGetLog() {
		Log l1 = LogFactory.getLog("test");
		Log l2 = LogFactory.getLog("test");
		assertSame(l1, l2);
	}

	@Test
	public void testLoadLogger() throws Exception {
		assertTrue(LogFactory.SELF.loadLogger("test") instanceof Slf4jLogger);

		Log l1 = LogFactory.SELF.loadLogger("test");
		Log l2 = LogFactory.SELF.loadLogger("test");
		assertNotSame(l1, l2);


		LogFactory.SELF.setClassLoader(new DummyClassLoader());
		Log l3 = LogFactory.SELF.loadLogger("test");
		assertNotSame(l1, l3);
	}

	@Test
	public void testGetDiagnosticContext() {
		assertTrue(LogFactory.getDiagnosticContext(new Slf4jLogger("test")) instanceof Slf4jDiagnosticContext);
		assertTrue(LogFactory.getDiagnosticContext(null) instanceof NoneDiagnosticContext);
	}

	@Test
	public void testSetClassLoader() {
		LogFactory.SELF.setClassLoader(ClassUtils.getDefaultClassLoader());
		assertEquals(LogFactory.SELF.loader, ClassUtils.getDefaultClassLoader());
	}

	static class DummyClassLoader extends ClassLoader {
	}
}
