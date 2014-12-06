package txt;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class TextNormalizer {
	private static TextNormalizer instance = null;
	private final static String DELIMITORS = "`~!#$%^&*()�?_+-=|}{[]\\/.,<>;:'\"”“»· –? \t\n";

	public static TextNormalizer getInstance() {
		if (instance == null)
			instance = new TextNormalizer();
		return instance;
	}

	public static List<String> tokenizeText(String input) {
		ArrayList<String> result = new ArrayList<String>();

		StringTokenizer st = new StringTokenizer(input, DELIMITORS);
		while (st.hasMoreElements()) {
			result.add(st.nextToken().trim());
		}

		return result;
	}

	public TextNormalizer() {
		StopWordDetector.getInstance();
	}

	private static boolean isNumber(String input) {
		return input.matches("-?\\d+(\\.\\d+)?");
	}

	public static List<String> normalize(String str) {
		List<String> terms = new ArrayList<String>();
		String[] parts = str.toLowerCase().trim().split(" ");

		for (String part : parts) {
			if (part.length() < 2 || part.startsWith("http")
					|| part.startsWith("ftp:") || part.equals("amp")
					|| part.equals("&amp") || part.contains("www")
					|| part.contains("http://"))
				continue;

			for (String token : tokenizeText(part)) {
				String term = token.replaceAll("'s", "").replaceAll("’t", "")
						.replaceAll("’s", "").replaceAll("'t", "").trim();

				if (StopWordDetector.isStopWord(term) || term.length() < 2
						|| isNumber(term))
					continue;

				term = term
						.replaceAll("[\\|()%~`<>.,+-/{/}:;@$!?^&*\"\\[\\]]",
								" ").replaceAll("[^a-zA-Z0-9 -]", "")
						.replaceAll(" ", "");

				if (term.length() > 1)
					terms.add(term);
			}
		}

		return terms;
	}
}
