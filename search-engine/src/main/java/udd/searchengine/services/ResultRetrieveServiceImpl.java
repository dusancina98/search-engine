package udd.searchengine.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;

import udd.searchengine.contracts.ResultRetrieveService;
import udd.searchengine.contracts.dto.SearchResultDTO;
import udd.searchengine.entities.elasticsearch.IndexUnit;

@Service
public class ResultRetrieveServiceImpl implements ResultRetrieveService{

	
	@Autowired
	private ElasticsearchRestTemplate elasticsearchRestTemplate;
	
	@Override
	public List<SearchResultDTO> getResultWithDynamicHighlight(QueryBuilder queryBuilder, HighlightBuilder highlightBuilder) {

		if (queryBuilder == null || highlightBuilder == null) {
			return null;
		}
		
		NativeSearchQuery searchQuery = new NativeSearchQueryBuilder()
		            .withQuery(queryBuilder)
		            .withHighlightBuilder(highlightBuilder)
		            .build();
		

		List<SearchResultDTO> results = new ArrayList<SearchResultDTO>();

		SearchHits<IndexUnit> searchHits = elasticsearchRestTemplate.search(searchQuery, IndexUnit.class);
		for (SearchHit<IndexUnit> searchHit : searchHits.getSearchHits()) {
			String highlight = "";
			Map<String, List<String>> highlightFieldsMap = searchHit.getHighlightFields();
			if (highlightFieldsMap.get(FIRST_NAME_FIELD) != null)
				highlight += highlightFieldsMap.get(FIRST_NAME_FIELD).get(0);
			
			if (highlightFieldsMap.get(LAST_NAME_FIELD) != null)
				highlight += " " + highlightFieldsMap.get(LAST_NAME_FIELD).get(0);
			
			highlight += "...";

			for (String key : highlightFieldsMap.keySet()) {
				if (key.equals(FIRST_NAME_FIELD) || key.equals(LAST_NAME_FIELD))
					continue;
				for (String searchHitContent :  highlightFieldsMap.get(key)) {
					highlight += searchHitContent + "...";
				}
			}
			IndexUnit indexUnit = searchHit.getContent();
			results.add(new SearchResultDTO(indexUnit.getId(), highlight, indexUnit.getFirstName(), indexUnit.getLastName(), indexUnit.getCity(), indexUnit.getQualificationLevel().name(), indexUnit.getLocation().getLat(), indexUnit.getLocation().getLon()));
			
		}


       
//        for (IndexUnit indexUnit : jobApplicationElasticSearchRepository.search(queryBuilder)) {
//        	String highlight = getDynamicHighlightFromText(indexUnit.getCoverLetterContent(), valueForDynamicHighlight);
//        	results.add(new SearchResultDTO(indexUnit.getId() ,highlight, indexUnit.getFirstName(), indexUnit.getLastName(), indexUnit.getCity(), indexUnit.getQualificationLevel().name(), indexUnit.getLocation().getLat(), indexUnit.getLocation().getLon()));
//		}
//        
		
		return results;
	}
	
	@Override
	public List<SearchResultDTO> getGeoSpatialResult(QueryBuilder queryBuilder) {
		if (queryBuilder == null) {
			return null;
		}

		List<SearchResultDTO> results = new ArrayList<SearchResultDTO>();

		BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery().must(queryBuilder);
		NativeSearchQuery searchQuery = new NativeSearchQueryBuilder()
				.withQuery(boolQueryBuilder)
				.build();
		SearchHits<IndexUnit> searchHits = elasticsearchRestTemplate.search(searchQuery, IndexUnit.class);

		for (SearchHit<IndexUnit> searchHit : searchHits.getSearchHits()) {
			IndexUnit indexUnit = searchHit.getContent();
        	String highlight = getStaticFromText(indexUnit.getCoverLetterContent());
        	results.add(new SearchResultDTO(indexUnit.getId() ,highlight, indexUnit.getFirstName(), indexUnit.getLastName(), indexUnit.getCity(), indexUnit.getQualificationLevel().name(), indexUnit.getLocation().getLat(), indexUnit.getLocation().getLon()));
		}

		return results;
	}
	
	private String getStaticFromText(String text) {
		String normalizedText = StringUtils.normalizeSpace(text);

		return normalizedText.substring(0, normalizedText.length() > STATIC_HIGHLIGHT_LENGTH ? STATIC_HIGHLIGHT_LENGTH : normalizedText.length() - 1) + "...";
	}
	
}
