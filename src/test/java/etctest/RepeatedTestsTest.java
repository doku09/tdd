package etctest;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.RepetitionInfo;
import org.junit.jupiter.api.TestReporter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RepeatedTestsTest {

	private static Set<Integer> integerSet = new HashSet<>();
	private static List<Integer> integerList = new ArrayList<>();

	@RepeatedTest(value = 5, name= "{displayName} - repetition {currentRepetition}/{totalRepetitions}")
	@DisplayName("Test add operation")
	void addNumber() {
		Calcualtor calcualtor = new Calcualtor();
		assertEquals(2, calcualtor.add(1,1), "1 + 1 should equal 2");
	}

	@RepeatedTest(value = 5, name= "the list contains {currentRepetition} elements(s), the set contains 1 element")
	void testAddingToCollections(TestReporter testReporter, RepetitionInfo repetitionInfo) {
		integerSet.add(1);
		integerList.add(repetitionInfo.getCurrentRepetition());

		testReporter.publishEntry("Repetition number",String.valueOf(repetitionInfo.getCurrentRepetition()));

		assertEquals(1, integerSet.size());
		assertEquals(repetitionInfo.getCurrentRepetition(),integerList.size());

	}
}

class Calcualtor {
	int a;
	int b;

	public int add(int a,int b) {
		return a + b;
	}
}
