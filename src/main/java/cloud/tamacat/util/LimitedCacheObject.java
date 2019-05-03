/*
 * Copyright (c) 2009 tamacat.org
 * All rights reserved.
 */
package cloud.tamacat.util;

public interface LimitedCacheObject {

	boolean isCacheExpired(long expire);
}
