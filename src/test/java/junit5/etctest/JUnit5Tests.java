package junit5.etctest;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("JUnit5를 학습하면서 예제를 테스트해본다.")
public class JUnit5Tests {

	@Test
	@DisplayName("assertAll은 excutable 객체가 하나라도 실패하면 첫번째 파라미터 메시지가 호출된다.")
	void testAssertAll() {
		String admin = "관리자";
		boolean isAdmin = true;
		assertAll("테스트가 정상적으로 동작하지 않았음",
			() -> assertEquals("관리자",admin),
			() -> assertFalse(isAdmin)
			);
	}

}
