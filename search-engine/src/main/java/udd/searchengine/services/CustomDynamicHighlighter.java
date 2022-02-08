package udd.searchengine.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

public class CustomDynamicHighlighter {

	public static String getDynamicHighlightFromText(String text, String valueForDynamicHighlight) {
		String normalizedText = StringUtils.normalizeSpace(text);

		if (valueForDynamicHighlight.isEmpty())
			return normalizedText.substring(0, normalizedText.length() > 200 ? 200 : normalizedText.length() - 1) + "...";
		
		String[] sentences = getSentencesFromString(normalizedText);
		
		return extractHighlightedText(sentences, valueForDynamicHighlight);
	}
	
	private static String extractHighlightedText(String[] sentences, String valueForDynamicHighlight) {
		List<Integer> foundInSentences = new ArrayList<Integer>();
		List<String> matchedSentences = new ArrayList<String>();
		Map<Integer, List<Integer>> indexesInSentences = new HashMap<Integer, List<Integer>>();
		
		for(int i = 0; i < sentences.length; i++) {
			if(sentences[i].toLowerCase().contains(valueForDynamicHighlight.toLowerCase())) {
				foundInSentences.add(i);
				matchedSentences.add(sentences[i]);
				List<Integer> indexes = new ArrayList<Integer>();
				
				for (int j = -1; (j = sentences[i].toLowerCase().indexOf(valueForDynamicHighlight.toLowerCase(), j + 1)) != -1; j++) {
					indexes.add(j);
				} 
				
				indexesInSentences.put(i, indexes);
			}
		}
		
		String highlight = "";
		
		for (int i = 0; i < foundInSentences.size(); i++) {
			if (i == 0 && foundInSentences.get(i) - 1 >= 0)
				highlight += "...";
			else if(i - 1 >= 0) {
				if (foundInSentences.get(i) - foundInSentences.get(i - 1) > 1)
					highlight += "...";
			}

			List<Integer> idxs = indexesInSentences.get(foundInSentences.get(i));

			for(int j = 0; j < idxs.size(); j++) {
				if (j == 0) {
					highlight += matchedSentences.get(i).substring(0, idxs.get(0));
				} else {
					if (j - 1 >= 0) {
						highlight += matchedSentences.get(i).substring(idxs.get(j-1) + valueForDynamicHighlight.length(), idxs.get(j));
					}
				}
				highlight += "<b>" + valueForDynamicHighlight + "</b>";

			}
			int lastIdx = idxs.get(idxs.size() - 1);
			highlight += matchedSentences.get(i).substring(lastIdx + valueForDynamicHighlight.length(), matchedSentences.get(i).length()) + ". ";

		}
		
		if (!foundInSentences.isEmpty()) {
			if (foundInSentences.get(foundInSentences.size() - 1) < sentences.length -1)
				highlight += "...";
		}		
		
		return highlight;
	}
	
	private static String[] getSentencesFromString(String text) {
		return text.split("(?<=[a-z])\\.\\s+");
	}
}
