package extension;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(CustomExtension.class)
public class JUnit5CustomExtensionTester {

	@Test
	void myCustomRuleTest() {
		System.out.println("Call of a test method");
	}

}
