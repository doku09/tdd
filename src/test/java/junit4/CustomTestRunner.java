package junit4;

import org.junit.jupiter.api.Test;
import org.junit.runner.Description;
import org.junit.runner.Runner;
import org.junit.runner.notification.RunNotifier;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class CustomTestRunner extends Runner {

	private Class<?> testedClass;

	public CustomTestRunner(Class<?> testedClass) {
		this.testedClass = testedClass;
	}

	@Override
	public Description getDescription() {
		return Description.createTestDescription(testedClass, this.getClass().getSimpleName() + " description");
	}

	@Override
	public void run(RunNotifier runNotifier) {
		System.out.println("Running tests with " + this.getClass().getSimpleName() + ": " + testedClass);

		try {
			Object testObject = testedClass.newInstance();
			for (Method method : testedClass.getMethods()) {
				if(method.isAnnotationPresent(Test.class)) {
				runNotifier.fireTestStarted(Description.createTestDescription(testedClass, method.getName()));
				method.invoke(testObject);
				runNotifier.fireTestFinished(Description.createTestDescription(testedClass, method.getName()));
				}
			}
		} catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
			throw new RuntimeException(e);
		}
	}
}
