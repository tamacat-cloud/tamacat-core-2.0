package cloud.tamacat.util;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class RateLimiterTest {

	@Test
	void testCreate() {
		RateLimiter limiter = RateLimiter.create(3);
		assertNotNull(limiter);
	}

	@Test
	void testAddAndCheck() throws Exception {
		RateLimiter limiter = RateLimiter.create(3);
		limiter.addAndCheck("test", 500);
		assertEquals(1, limiter.count("test"));
		
		Thread.sleep(1000);
		assertEquals(0, limiter.count("test"));
	}

	@Test
	void testCount() {
		RateLimiter limiter = RateLimiter.create(3);
		assertEquals(0, limiter.count("test"));
	}

	@Test
	void testRemove() {
		RateLimiter limiter = RateLimiter.create(3);
		limiter.remove("test");
	}

}
