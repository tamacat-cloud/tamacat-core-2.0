package cloud.tamacat.util;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class PropertyPlaceholderHelperTest {

	@Test
	void testParseStringValue() {
		String value = "http://${TEST_HOST:localhost}:${TEST_PORT:80}/${ROOT_PATH:test}/index.html";
		assertEquals("http://localhost:80/test/index.html", SystemPropertyUtils.resolvePlaceholders(value, true));
	}

}
