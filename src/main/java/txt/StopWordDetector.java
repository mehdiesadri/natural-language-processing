package txt;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashSet;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class StopWordDetector {
	Logger logger = LogManager.getLogger(StopWordDetector.class.getName());

	private HashSet<String> stopWords;

	public StopWordDetector(String path) {
		stopWords = new HashSet<String>();

		if (path != null && path.length() > 0) {
			try {
				FileReader reader = new FileReader(path);
				BufferedReader br = new BufferedReader(reader);
				String line = br.readLine();

				while (line != null) {
					stopWords.add(line.trim());
					line = br.readLine();
				}

				br.close();
				reader.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		logger.info("StopWordDetector has been configured with "
				+ stopWords.size() + " stop words.");
	}

	public boolean isStopWord(String word) {
		if (stopWords.contains(word))
			return true;

		return false;
	}
}
