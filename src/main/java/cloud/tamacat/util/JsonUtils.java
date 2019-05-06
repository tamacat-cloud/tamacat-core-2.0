package cloud.tamacat.util;

import java.util.function.BiConsumer;
import java.util.function.BiFunction;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;

public class JsonUtils {

	public static BiFunction<JsonObject, String, String> getString = (JsonObject data, String key) -> {
		if (data.containsKey(key)) {
			return data.getString(key);
		}
		return "";
	};
	
	public static BiFunction<JsonObject, String, Integer> getInt = (JsonObject data, String key) -> {
		if (data.containsKey(key)) {
			return data.getInt(key);
		}
		return null;
	};
	
	public static BiFunction<JsonObject, String, Boolean> getBoolean = (JsonObject data, String key) -> {
		if (data.containsKey(key)) {
			return data.getBoolean(key);
		}
		return null;
	};

	public static BiFunction<JsonObject, String, JsonObject> getObject = (JsonObject data, String key) -> {
		if (data.containsKey(key)) {
			return data.getJsonObject(key);
		}
		return Json.createObjectBuilder().build();
	};
	
	public static BiFunction<JsonObject, String, JsonArray> getArray = (JsonObject data, String key) -> {
		if (data.containsKey(key)) {
			return data.getJsonArray(key);
		}
		return Json.createArrayBuilder().build();
	};
	
	public static String stringify(String json) {
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
				} else {
					//delete spaces
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
}
