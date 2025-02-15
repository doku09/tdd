package junit5.etctest;


import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParameterizedWithValueSourceTest {

	private WordCounter wordCounter = new WordCounter();

	@ParameterizedTest
	@ValueSource(strings = {"Check three parameters","JUnit in Action"})
	void testWordsInSentenceValues(String sentence) {
		assertEquals(3, wordCounter.countWords(sentence));
	}

	@ParameterizedTest
	@EnumSource(Sentences.class)
	void testWordsInSentenceEnums(Sentences sentence) {
		assertEquals(3, wordCounter.countWords(sentence.value()));
	}

	@ParameterizedTest
	@EnumSource(value = Sentences.class,
	names = {"JUNIT_IN_ACTION","THREE_PARAMETERS"})
	void testSelectedWordsInSentence(Sentences sentence) {
		assertEquals(3, wordCounter.countWords(sentence.value()));
	}

	@ParameterizedTest
	@EnumSource(value = Sentences.class, mode = EnumSource.Mode.EXCLUDE,
		names = {"THREE_PARAMETERS"})
	void testExcludeWordsInSentence(Sentences sentence) {
		assertEquals(3, wordCounter.countWords(sentence.value()));
	}


	static class WordCounter{
		int countWords(String word) {
			return word.split(" ").length;
		}
	}
}

enum Sentences {
	JUNIT_IN_ACTION("JUnit in Action"),
	SOME_PARAMETERS("Check some parameters"),
	THREE_PARAMETERS("Check three parameters");

	private final String sentence;

	Sentences(String sentence) {
		this.sentence = sentence;
	}
	public String value() {
		return sentence;
	}
}
