package lang;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class EnglishClassifier implements LanguageClassifier {
	private static EnglishClassifier instance = null;
	private static ArrayList<String> dictionary;
	private static double threshold;

	public static EnglishClassifier getInstance(double th, String dicPath) {
		if (instance == null)
			instance = new EnglishClassifier(th, dicPath);
		return instance;
	}

	public EnglishClassifier(double th, String dicPath) {
		dictionary = new ArrayList<String>();
		threshold = th;

		FileReader fileReader;
		try {
			fileReader = new FileReader(dicPath);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			String line = null;

			while ((line = bufferedReader.readLine()) != null)
				dictionary.add(line.toLowerCase().trim());

			bufferedReader.close();
			fileReader.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static boolean isEnglish(List<String> words) {
		int numberOfEnglishWords = 0;
		int numberOfWords = words.size();

		for (String word : words) {
			if (dictionary.contains(word.toLowerCase()) || isEnglishName(word))
				numberOfEnglishWords++;
		}

		double prob = (double) numberOfEnglishWords / (double) numberOfWords;

		if (prob >= threshold)
			return true;
		else
			return false;
	}

	public boolean satisfy(List<String> words) {
		return isEnglish(words);
	}

	private static boolean isEnglishName(String word) {
		return word.matches("[A-Z][a-zA-Z0-9]+");
	}
}
