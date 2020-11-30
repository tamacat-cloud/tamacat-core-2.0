/*
 * Copyright 2020 tamacat.org
 * Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0
 */
package cloud.tamacat.util;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class JsonObjectBuilder {

	JsonObject json;
	
	public static JsonObjectBuilder create() {
		return new JsonObjectBuilder();
	}
	
	public JsonObjectBuilder() {
		this.json = new JsonObject();
	}
	
	public JsonObjectBuilder add(String name, String value) {
		json.addProperty(name, value);
		return this;
	}
	
	public JsonObjectBuilder add(String name, Boolean value) {
		json.addProperty(name, value);
		return this;
	}
	
	public JsonObjectBuilder add(String name, Number value) {
		json.addProperty(name, value);
		return this;
	}
	
	public JsonObjectBuilder add(String name, Character value) {
		json.addProperty(name, value);
		return this;
	}
	
	public JsonObjectBuilder add(String name, JsonElement value) {
		json.add(name, value);
		return this;
	}
	
	public JsonObjectBuilder add(String name, JsonObjectBuilder value) {
		json.add(name, value.build());
		return this;
	}
	
	public JsonObjectBuilder add(String name, JsonArrayBuilder value) {
		json.add(name, value.build());
		return this;
	}
	
	public JsonObject build() {
		return json;
	}
}
