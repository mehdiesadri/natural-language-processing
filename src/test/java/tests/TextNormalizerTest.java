package tests;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import txt.TextNormalizer;

public class TextNormalizerTest {

	@Before
	public void setUp() throws Exception {
		TextNormalizer.getInstance();
	}

	@Test
	public void test() {
		String inputStr = "my name is mehdi~ 765";
		List<String> normalTermList = TextNormalizer.normalize(inputStr);
		assertFalse(normalTermList.contains("765"));
		assertFalse(normalTermList.contains("my"));
		assertTrue(normalTermList.contains("mehdi"));

	}
}
