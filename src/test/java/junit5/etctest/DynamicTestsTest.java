package junit5.etctest;

import org.junit.jupiter.api.*;

import java.util.Iterator;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.DynamicTest.dynamicTest;

public class DynamicTestsTest {

	PositiveNumChecker predicate = new PositiveNumChecker();

	@BeforeAll
	static void setUpClass() {
		System.out.println("DynamicTestsTest.BeforeAll");
	}

	@AfterAll
	static void tearDownClass() {
		System.out.println("DynamicTestsTest.AfterAll");
	}

	@BeforeEach
	void setUp() {
		System.out.println("DynamicTestsTest.BeforeEach");
	}

	@AfterEach
	void tearDown() {
		System.out.println("DynamicTestsTest.AfterEach");
	}

	@Test
	void isPositive() {
		System.out.println("DynamicTestsTest.isPositive");
		assertTrue(() -> predicate.check(1));
	}
	@TestFactory
	Iterator<DynamicTest> positiveNumberCheckTestCases() {
		System.out.println("TestFactory 실행");
		return asList(
			dynamicTest("음수인지 확인한다.",
				() -> assertFalse(predicate.check(-1))),
			dynamicTest("0인지 확인한다.",
				() -> assertFalse(predicate.check(0))),
			dynamicTest("양수인지 확인한다.",
				() -> assertTrue(predicate.check(1)))
		).iterator();
	}
	static class PositiveNumChecker {
		public PositiveNumChecker() {
		}

		boolean check(int number) {
			return number > 0;
		}
	}
}
