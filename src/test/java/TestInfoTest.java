import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TestInfoTest {
	public TestInfoTest(TestInfo testInfo) {
		assertEquals("TestInfoTest", testInfo.getDisplayName());
	}

	@BeforeEach
	void setUp(TestInfo testInfo) {
		String displayName = testInfo.getDisplayName();
		assertTrue(displayName.equals("display name of the method") || displayName.equals("testGetNameOfTheMethod(TestInfo)"));
	}

	@Test
	@DisplayName("display name of the method")
	void testGetNameOftheMethodWIthDisplayNameAnnotaion(TestInfo testInfo) {
		assertEquals("display name of the method", testInfo.getDisplayName());
	}
}
