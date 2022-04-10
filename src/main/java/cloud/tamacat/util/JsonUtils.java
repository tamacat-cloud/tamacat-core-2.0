/*
 * Copyright 2019 tamacat.org
 * Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0
 */
package cloud.tamacat.util;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class JsonUtils {

	public static <T>T fromJsonInFileOrClasspathInputStream(String file, Class<T> type) {
		return fromJson(IOUtils.getFileOrClasspathInputStream(file, ClassUtils.getDefaultClassLoader()), type);
	}
	
	public static <T>T fromJsonInClasspath(String file, Class<T> type) {
		return fromJson(IOUtils.getInputStream(file), type);
	}
	
	public static <T>T fromJson(InputStream in, Class<T> type) {
		return fromJson(new InputStreamReader(in, StandardCharsets.UTF_8), type);
	}
	
	public static <T>T fromJson(Reader reader, Class<T> type) {
		return new Gson().fromJson(reader, type);
	}
	
	public static <T>T fromJson(String json, Class<T> type) {
		return new Gson().fromJson(json, type);
	}
	
	public static String toJson(Object o) {
		return new GsonBuilder().create().toJson(o);
	}
	
	public static String stringify(Object o) {
		return new GsonBuilder().setPrettyPrinting().create().toJson(o);
	}
	
	public static String format(String json) {
		BiConsumer<StringBuilder, Integer> newLine = (StringBuilder builder, Integer indent) -> {
			builder.append("\n");
			for (int i=0; i<indent; i++) {
				builder.append("  ");
			}
		};
		
		StringBuilder builder = new StringBuilder();
		int indent = 0;
		boolean quoted = false;
		for (char c : json.toCharArray()) {
			if ('"' == c) {
				builder.append(c);
				quoted = !quoted;
			} else if (' ' == c) {
				if (quoted) {
					builder.append(c);
				}
			} else if ('{' == c || '[' == c) {
				builder.append(c);
				indent++;
				newLine.accept(builder, indent);
			} else if ('}' == c || ']' == c) {
				indent--;
				newLine.accept(builder, indent);
				builder.append(c);
			} else if (',' == c) {
				builder.append(c);
				if (!quoted) {
					newLine.accept(builder, indent);
				}
			} else {
				builder.append(c);
			}
		}
		return builder.toString();
	}

	public static BiFunction<JsonObject, String, String> getString = (JsonObject data, String key) -> {
		if (data.has(key)) {
			return data.get(key).getAsString();
		}
		return "";
	};
	
	public static BiFunction<JsonObject, String, Integer> getInt = (JsonObject data, String key) -> {
		if (data.has(key)) {
			return data.get(key).getAsInt();
		}
		return null;
	};
	
	public static BiFunction<JsonObject, String, Boolean> getBoolean = (JsonObject data, String key) -> {
		if (data.has(key)) {
			return data.get(key).getAsBoolean();
		}
		return false;
	};

	public static BiFunction<JsonObject, String, JsonObject> getObject = (JsonObject data, String key) -> {
		if (data.has(key)) {
			return data.get(key).getAsJsonObject();
		}
		return new JsonObject();
	};
	
	public static BiFunction<JsonObject, String, JsonArray> getArray = (JsonObject data, String key) -> {
		if (data.has(key)) {
			return data.get(key).getAsJsonArray();
		}
		return new JsonArray();
	};
}
