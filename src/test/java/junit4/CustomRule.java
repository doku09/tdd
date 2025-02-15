package junit4;

import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

public class CustomRule implements TestRule {

	private Statement base;
	private Description description;

	@Override
	public Statement apply(Statement statement, Description description) {
		this.base =statement;
		this.description = description;
		return new CustomStatement(base, description);
	}
}
