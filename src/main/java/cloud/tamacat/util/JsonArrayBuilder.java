/*
 * Copyright 2020 tamacat.org
 * Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0
 */
package cloud.tamacat.util;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;

public class JsonArrayBuilder {
	
	JsonArray json;
	
	public static JsonArrayBuilder create() {
		return new JsonArrayBuilder();
	}
	
	public JsonArrayBuilder() {
		this.json = new JsonArray();
	}
	
	public JsonArrayBuilder add(String value) {
		json.add(value);
		return this;
	}
	
	public JsonArrayBuilder add(Boolean value) {
		json.add(value);
		return this;
	}
	
	public JsonArrayBuilder add(Number value) {
		json.add(value);
		return this;
	}
	
	public JsonArrayBuilder add(Character value) {
		json.add(value);
		return this;
	}
	
	public JsonArrayBuilder add(JsonElement value) {
		json.add(value);
		return this;
	}
	
	public JsonArray build() {
		return json;
	}
}
