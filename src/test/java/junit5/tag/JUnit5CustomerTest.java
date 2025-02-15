package junit5.tag;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;

@Tag("individual")
public class JUnit5CustomerTest {
	private String CUSTOMER_NAME = "John Smith";

	@Test
	void testCustomer() {
		Customer customer = new Customer(CUSTOMER_NAME);
		assertEquals("John Smith", customer.getName());
	}
}

class Customer {

	private String name;

	public Customer() {
	}

	public Customer(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
}
