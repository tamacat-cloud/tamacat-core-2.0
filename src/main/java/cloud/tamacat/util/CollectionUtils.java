/*
 * Copyright 2008 tamacat.org
 * Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0
 */
package cloud.tamacat.util;

import java.util.Collection;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Utilities of Collection.
 */
public abstract class CollectionUtils {
	
	public static <K, V> Map<K, V> convert(
			final Map<K, V> map, final Collection<K> keys, final Collection<V> values) {
		if (keys.size() != values.size()) {
			throw new IllegalArgumentException("Invalid Collection. Collection size is not equals.");
		}
		Iterator<V> valueIt = values.iterator();
		for (K key : keys) {
			map.put(key, valueIt.next());
		}
		return map;
	}
	
	public static <E> List<E> newArrayList() {
		return new ArrayList<E>();
	}
	
	public static <E> Set<E> newHashSet() {
		return new HashSet<E>();
	}
	
	/**
	 * @since 2.0
	 */
	public static <E> Set<E> newLinkedHashSet() {
		return new LinkedHashSet<E>();
	}
	
	public static <K, V> Map<K, V> newHashMap() {
		return new HashMap<K, V>();
	}
	
	public static <K, V> Map<K, V> newLinkedHashMap() {
		return new LinkedHashMap<K, V>();
	}
}
