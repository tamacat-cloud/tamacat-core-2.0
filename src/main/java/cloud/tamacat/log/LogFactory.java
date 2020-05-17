/*
 * Copyright (c) 2007 tamacat.org
 * All rights reserved.
 */
package cloud.tamacat.log;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import cloud.tamacat.log.impl.JDKLogger;
import cloud.tamacat.log.impl.Log4j2DiagnosticContext;
import cloud.tamacat.log.impl.Log4j2Logger;
import cloud.tamacat.log.impl.NoneDiagnosticContext;
import cloud.tamacat.log.impl.SimpleLogger;
import cloud.tamacat.log.impl.Slf4jDiagnosticContext;
import cloud.tamacat.log.impl.Slf4jLogger;
import cloud.tamacat.util.ClassUtils;
import cloud.tamacat.util.PropertyUtils;

/**
 * Usage:
 * {@code
 *   static final Log LOG = LogFactory.getLog(Target.class);
 * }
 */
public class LogFactory {

	static final String LOGGING_PROPERTIES_FILE = "logging.properties";
	static final String KEY = "cloud.tamacat.log.Log";
	static final String LOG4J2_CLASS = "org.apache.logging.log4j.Logger";
	static final String SLF4J_CLASS = "org.slf4j.Logger";

	static LogFactory SELF = new LogFactory();
	private static final Map<String, Log> manager = new HashMap<>();

	protected ClassLoader loader = ClassUtils.getDefaultClassLoader();

	public synchronized static Log getLog(String categoryName) {
		Log logger = manager.get(categoryName);
		if (logger == null) {
			try {
				logger = SELF.loadLogger(categoryName);
				if (logger != null) manager.put(categoryName, logger);
			} catch (Exception e) {
				new JDKLogger(LogFactory.class.getName()).error(e.getMessage(), e);
			}
		}
		return logger;
	}

	public synchronized static Log getLog(Class<?> className) {
		return getLog(className.getName());
	}

	private LogFactory(){}

	protected Log loadLogger(String name) {
		Log logger = null;
		try {
			Properties props = PropertyUtils.getProperties(LOGGING_PROPERTIES_FILE, loader);
			if (props != null) {
				String className = props.getProperty(KEY);
				Class<?> clazz = ClassUtils.forName(className, loader);
				if (clazz != null) {
					logger = (Log) ClassUtils.newInstance(clazz, String[].class, name);
					if (logger != null) {
						return logger;
					}
				}
			}
		} catch (Exception e) {
		}
		Class<?> slf4jClass = ClassUtils.forName(SLF4J_CLASS, loader);
		if (slf4jClass != null) {
			return new Slf4jLogger(name);
		}
		Class<?> log4j2Class = ClassUtils.forName(LOG4J2_CLASS, loader);
		if (log4j2Class != null) {
			return new Log4j2Logger(name);
		}
		return new SimpleLogger();
	}

	public static DiagnosticContext getDiagnosticContext(Log logger) {
		if (logger instanceof Slf4jLogger) {
            return new Slf4jDiagnosticContext();
		} else if (logger instanceof Log4j2Logger) {
			return new Log4j2DiagnosticContext();
		} else {
			return new NoneDiagnosticContext();
		}
	}

	public void setClassLoader(ClassLoader loader) {
		this.loader = loader;
	}
}
