/*
 * Copyright 2008 tamacat.org
 * Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0
 */
package cloud.tamacat.log.impl;

import java.util.logging.Handler;
import java.util.logging.Level;

public class JDKLogger extends AbstractLogger<Level> {

	static {
		System.setProperty("java.util.logging.config.file", "logging.properties");
	}

	private java.util.logging.Logger logger;

	public JDKLogger(String name) {
		logger = java.util.logging.Logger.getLogger(name);
	}

	void setHandler(Handler handler) {
		logger.addHandler(handler);
	}

	@Override
	protected Level getDebugLevel() {
		return Level.FINE;
	}

	@Override
	protected Level getErrorLevel() {
		return Level.SEVERE;
	}

	@Override
	protected Level getFatalLevel() {
		return Level.SEVERE;
	}

	@Override
	protected Level getInfoLevel() {
		return Level.INFO;
	}

	@Override
	protected Level getTraceLevel() {
		return Level.FINEST;
	}

	@Override
	protected Level getWarnLevel() {
		return Level.WARNING;
	}

	@Override
	protected boolean isEnabled(Level level) {
		return logger.isLoggable(level);
	}

	@Override
	protected void log(Level level, Object message) {
		if (message != null) {
			logger.log(level, message.toString());
		} else {
			logger.log(level, (String) null);
		}
	}

	public Object getOriginalLogger() {
		return logger;
	}
}
