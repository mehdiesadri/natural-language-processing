package txt;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashSet;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class StopWordDetector {
	Logger logger = LogManager.getLogger(StopWordDetector.class.getName());

	private static StopWordDetector instance = null;
	private static HashSet<String> stopWords;

	public static StopWordDetector getInstance() {
		if (instance == null)
			instance = new StopWordDetector();
		return instance;
	}

	public StopWordDetector() {
		stopWords = new HashSet<String>();

		try {
			InputStream in = StopWordDetector.class
					.getResourceAsStream("/stopwords.txt");
			InputStreamReader insr = new InputStreamReader(in);
			BufferedReader br = new BufferedReader(insr);
			String line = null;
			line = br.readLine();

			while (line != null) {
				stopWords.add(line.trim());
				line = br.readLine();
			}

			br.close();
			in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		logger.info("StopWordDetector has been configured with "
				+ stopWords.size() + " stop words.");
	}

	public static boolean isStopWord(String word) {
		if (stopWords.contains(word))
			return true;

		return false;
	}
}
