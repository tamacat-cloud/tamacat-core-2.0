package cloud.tamacat.util;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Properties;

import org.junit.jupiter.api.Test;

class SystemPropertyUtilsTest {

	@Test
	void testResolvePlaceholders_Properties() {
		System.setProperty("TEST_URL", "http://localhost");

		Properties props = new Properties();
		props.setProperty("test.url", "${TEST_URL:http://example.com}/test/");
		props.setProperty("test.url2", "${TEST_URL2:http://example.com}/test/");
		
		Properties result = SystemPropertyUtils.resolvePlaceholders(props);
		assertEquals("http://localhost/test/", result.getProperty("test.url"));

		assertEquals("http://example.com/test/", result.getProperty("test.url2"));
	}
}
