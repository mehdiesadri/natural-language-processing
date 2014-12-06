package tests;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;

import lang.EnglishClassifier;

import org.junit.Before;
import org.junit.Test;

public class EnglishClassifierTest {

	@Before
	public void setUp() throws Exception {
		EnglishClassifier.getInstance(.7);
	}

	@Test
	public void test() {
		String str1 = "my name is mehdi";
		String str2 = "il mio nome è Mehdi";

		List<String> str1List = Arrays.asList(str1.split(" "));
		List<String> str2List = Arrays.asList(str2.split(" "));

		assertTrue(EnglishClassifier.isEnglish(str1List));
		assertFalse(EnglishClassifier.isEnglish(str2List));
	}
}
