package junit5;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class JUnit5TempDirTester {
	@TempDir
	Path tempDir;

	private static  Path createdFile;

	@Test
	@DisplayName("임시파일이 사라지는지 테스트")
	void testTemporaryFolder() throws IOException {

	  //given

	  //when

	  //then
		assertTrue(Files.isDirectory(tempDir));
		createdFile = Files.createFile(tempDir.resolve("createdFile.txt"));
		assertTrue(createdFile.toFile().exists());
	}

	@AfterAll
	public static void afterAll() {
		assertFalse(createdFile.toFile().exists());
	}

}
