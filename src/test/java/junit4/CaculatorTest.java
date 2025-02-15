package junit4;

import junit5.Calculator;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(CustomTestRunner.class)
public class CaculatorTest {
	@Test
	public void testAdd() {
		Calculator calculator = new Calculator();
		double result = calculator.add(10,50);
		assertEquals(60,result,0);
	}
}
