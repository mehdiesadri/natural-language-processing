package lang;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PersianClassifier implements LanguageClassifier {
	private static PersianClassifier instance = null;
	private static ArrayList<String> dictionary;
	private static double threshold;

	public static PersianClassifier getInstance(double th, String dicPath) {
		if (instance == null)
			instance = new PersianClassifier(th, dicPath);
		return instance;
	}

	private static char[] arabicChars = { 'ْ', 'ٌ', 'ٍ', 'ً', 'ُ', 'ِ', 'َ',
			'ّ', 'ؤ', 'إ', 'أ', 'ة', 'ء', 'ّ', 'ً', 'ُ', 'ٌ', 'ﻹ', 'إ', '`',
			'ِ', 'ٍ', 'ﻷ', 'ْ', 'ﻵ', 'ّ' };

	public PersianClassifier(double th, String dicPath) {
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

	public static boolean isPersian(List<String> words) {
		return isPersian((String[]) words.toArray());
	}

	public static double isPersian(String word) {
		if (dictionary.contains(word))
			return 1;

		for (char c : arabicChars) {
			String x = "";
			x = x + c;
			if (word.contains(x))
				return 0;
		}

		if (word.contains("پ") || word.contains("ژ") || word.contains("گ"))
			return .8;

		if (isArabic(word))
			return .2;

		return .5;

	}

	public static boolean isPersian(String[] words) {
		double numberOfPersianWords = 0;
		int numberOfWords = words.length;

		for (String word : words) {
			numberOfPersianWords += isPersian(word);
		}

		double prob = numberOfPersianWords / numberOfWords;

		if (prob >= threshold)
			return true;
		else
			return false;
	}

	public boolean satisfy(List<String> words) {
		return isPersian(words);
	}

	private static boolean isArabic(String word) {
		if (word.startsWith("ال") || word.startsWith("لل"))
			return true;
		return false;
	}

}