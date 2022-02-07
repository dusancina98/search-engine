package udd.searchengine.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import udd.searchengine.contracts.ResultRetrieveService;
import udd.searchengine.contracts.dto.SearchResultDTO;
import udd.searchengine.entities.elasticsearch.IndexUnit;
import udd.searchengine.repository.elasticsearch.JobApplicationElasticSearchRepository;

@Service
public class ResultRetrieveServiceImpl implements ResultRetrieveService{

	@Autowired
	private JobApplicationElasticSearchRepository jobApplicationElasticSearchRepository;
	
	@SuppressWarnings("deprecation")
	@Override
	public List<SearchResultDTO> getResultWithDynamicHighlight(QueryBuilder queryBuilder, String valueForDynamicHighlight) {
		if (queryBuilder == null) {
			return null;
		}

		List<SearchResultDTO> results = new ArrayList<SearchResultDTO>();
       
        for (IndexUnit indexUnit : jobApplicationElasticSearchRepository.search(queryBuilder)) {
        	String highlight = getDynamicHighlightFromText(indexUnit.getCoverLetterContent(), valueForDynamicHighlight);
        	results.add(new SearchResultDTO(indexUnit.getId() ,highlight, indexUnit.getFirstName(), indexUnit.getLastName(), indexUnit.getCity(), indexUnit.getQualificationLevel().name(), indexUnit.getLocation().getLat(), indexUnit.getLocation().getLon()));
		}
        
		
		return results;
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public List<SearchResultDTO> getGeoSpatialResult(QueryBuilder queryBuilder) {
		if (queryBuilder == null) {
			return null;
		}

		List<SearchResultDTO> results = new ArrayList<SearchResultDTO>();

		BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery().must(queryBuilder);
        for (IndexUnit indexUnit : jobApplicationElasticSearchRepository.search(boolQueryBuilder)) {
        	String highlight = getDynamicHighlightFromText(indexUnit.getCoverLetterContent(), "");
        	results.add(new SearchResultDTO(indexUnit.getId() ,highlight, indexUnit.getFirstName(), indexUnit.getLastName(), indexUnit.getCity(), indexUnit.getQualificationLevel().name(), indexUnit.getLocation().getLat(), indexUnit.getLocation().getLon()));
		}

		return results;
	}
	
	private String getDynamicHighlightFromText(String text, String valueForDynamicHighlight) {
		String normalizedText = StringUtils.normalizeSpace(text);

		if (valueForDynamicHighlight.isEmpty())
			return normalizedText.substring(0, normalizedText.length() > 200 ? 200 : normalizedText.length() - 1) + "...";
		
		String[] sentences = getSentencesFromString(normalizedText);
		List<Integer> foundInSentences = new ArrayList<Integer>();
		List<String> matchedSentences = new ArrayList<String>();
		Map<Integer, List<Integer>> indexesInSentences = new HashMap<Integer, List<Integer>>();
		
		for(int i = 0; i < sentences.length; i++) {
			if(sentences[i].toLowerCase().contains(valueForDynamicHighlight.toLowerCase())) {
				foundInSentences.add(i);
				matchedSentences.add(sentences[i]);
				List<Integer> indexes = new ArrayList<Integer>();
				
				for (int j = -1; (j = sentences[i].toLowerCase().indexOf(valueForDynamicHighlight.toLowerCase(), j + 1)) != -1; j++) {
					System.out.println(j);
					indexes.add(j);
				} 
				
				indexesInSentences.put(i, indexes);
			}
		}
		System.out.println(indexesInSentences);
		
		String highlight = "";
		
		for (int i = 0; i < foundInSentences.size(); i++) {
			if (i == 0 && foundInSentences.get(i) - 1 >= 0)
				highlight += "...";
			else if(i + 1 <= foundInSentences.size() - 1) {
				if (foundInSentences.get(i + 1) - foundInSentences.get(i) > 1)
				highlight += "...";
			}
			//highlight += matchedSentences.get(i);//"<b>" + valueForDynamicHighlight + "</b>";
			List<Integer> idxs = indexesInSentences.get(foundInSentences.get(i));
			for(int j = 0; j < idxs.size(); j++) {
				if (j == 0) {
					highlight += matchedSentences.get(i).substring(0, idxs.get(0));
				} else {
					if (j + 1 < idxs.size()) {
						highlight += matchedSentences.get(i).substring(idxs.get(j), idxs.get(j + 1));
					}
				}
				highlight += "<b>" + valueForDynamicHighlight + "</b> ";
			}
			int lastIdx = idxs.get(idxs.size() - 1);
			highlight += matchedSentences.get(i).substring(lastIdx, matchedSentences.get(i).length() - 1);

		}
		
		if (!foundInSentences.isEmpty()) {
			if (foundInSentences.get(foundInSentences.size() - 1) < sentences.length -1)
				highlight += "...";
		}		
		return highlight;
	}
	
	private String[] getSentencesFromString(String text) {
		return text.split("(?<=[a-z])\\.\\s+");
	}

}
