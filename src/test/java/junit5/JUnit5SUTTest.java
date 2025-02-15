package junit5;

import org.junit.jupiter.api.*;

/**
 * 생애주기 테스트 -BeforeAll,AfterAll,BeforeEach,AfterEach
 */
//@TestInstance(TestInstance.Lifecycle.PER_CLASS) //BeforeAll, AfterAll이 비정적이어도 됨.
public class JUnit5SUTTest {


	@BeforeAll
	static void setupClass() {
		// 왜 static이어야 하는가? 리플렉션?
		System.out.println("beforeAll");
	}

	@AfterAll
	static void tearDownClass() {
		System.out.println("JUnit5SUTTest.tearDownClass");
	}

	@BeforeEach
	void setUp() {
		System.out.println("JUnit5SUTTest.BeforeEach");
	}

	@AfterEach
	void tearDown() {
		System.out.println("JUnit5SUTTest.AfterEach");
	}

	@Test
	public void testRegularWork() {
		System.out.println("JUnit5SUTTest.testRegularWork");
	}

	@Test
	void testAdditionalWOrk() {
		System.out.println("JUnit5SUTTest.testAdditionalWOrk");
	}

	@Test
	@Disabled
	void myThirdTest() {
		System.out.println("JUnit5SUTTest.myTHirdTest");
	}
}
