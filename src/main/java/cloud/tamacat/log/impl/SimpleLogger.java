/*
 * Copyright 2007 tamacat.org
 * Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0
 */
package cloud.tamacat.log.impl;

import java.io.PrintStream;

import cloud.tamacat.util.DateUtils;

public class SimpleLogger extends AbstractLogger<SimpleLevel> {

	protected PrintStream out;

	protected SimpleLevel level = SimpleLevel.INFO; // default

	public SimpleLogger() {
		this.out = System.out;
	}

	public SimpleLogger(PrintStream out) {
		this.out = out;
	}

	static final String TIMESTAMP_FORMAT_PATTERN = "yyyy-MM-dd HH:mm:ss,S";

	@Override
	protected void log(SimpleLevel level, Object message) {
		if (isEnabled(level)) {
			out.println(DateUtils.getTimestamp(TIMESTAMP_FORMAT_PATTERN) + " [" + Thread.currentThread().getName()
					+ "] " + level + " - " + message);
		}
	}

	@Override
	protected boolean isEnabled(SimpleLevel level) {
		return level.getPriority() >= this.level.getPriority();
	}

	@Override
	protected SimpleLevel getDebugLevel() {
		return SimpleLevel.DEBUG;
	}

	@Override
	protected SimpleLevel getErrorLevel() {
		return SimpleLevel.ERROR;
	}

	@Override
	protected SimpleLevel getFatalLevel() {
		return SimpleLevel.FATAL;
	}

	@Override
	protected SimpleLevel getInfoLevel() {
		return SimpleLevel.INFO;
	}

	@Override
	protected SimpleLevel getTraceLevel() {
		return SimpleLevel.TRACE;
	}

	@Override
	protected SimpleLevel getWarnLevel() {
		return SimpleLevel.WARN;
	}

	public Object getOriginalLogger() {
		return this;
	}
}
