/*
 * Copyright 2020 tamacat.org
 * Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0
 */
package cloud.tamacat.util;

import java.io.Reader;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class Json {

	public static JsonObjectBuilder createObjectBuilder() {
		return new JsonObjectBuilder();
	}
	
	public static JsonArrayBuilder createArrayBuilder() {
		return new JsonArrayBuilder();
	}
	
	public static JsonObject readObject(Reader reader) {
		return JsonParser.parseReader(reader).getAsJsonObject();
	}
	
	public static JsonArray readArray(Reader reader) {
		return JsonParser.parseReader(reader).getAsJsonArray();
	}
	
	public static JsonElement parseReader(Reader reader) {
		return JsonParser.parseReader(reader);
	}
}
