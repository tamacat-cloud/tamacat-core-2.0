/*
 * Copyright (c) 2008 tamacat.org
 * All rights reserved.
 */
package cloud.tamacat.util;

import java.util.UUID;

public class UniqueCodeGenerator {

	public static String generate() {
		return generate(false);
	}
	
	/**
	 * Generate random Unique ID
	 * @since 1.4
	 * @param deleteDelimiter replace "-" to blank
	 */
	public static String generate(boolean deleteDelimiter) {
		String uuid = UUID.randomUUID().toString();
		if (deleteDelimiter) {
			return uuid.replace("-", "");
		} else {
			return uuid;
		}
	}
	
	public static String generate(String prefix) {
		return prefix != null ? prefix + generate() : generate();
	}
}
