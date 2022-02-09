package udd.searchengine.services;

import java.util.List;
import java.util.UUID;

import org.apache.lucene.queryparser.classic.ParseException;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import udd.searchengine.contracts.ResultRetrieveService;
import udd.searchengine.contracts.SearchService;
import udd.searchengine.contracts.dto.SearchResultDTO;
import udd.searchengine.contracts.dto.SimpleQueryWithOperatorDTO;
import udd.searchengine.entities.City;
import udd.searchengine.entities.elasticsearch.LogicalOperator;
import udd.searchengine.entities.elasticsearch.SearchType;
import udd.searchengine.repository.CityRepository;
import udd.searchengine.services.util.elasticsearch.QueryFactory;

@Service
public class SearchServiceImpl implements SearchService{
	
	@Autowired
	private CityRepository cityRepository;
	
	@Autowired
	private ResultRetrieveService resultRetrieveService;	
	
	@Override
	public List<SearchResultDTO> search(List<SimpleQueryWithOperatorDTO> queries) {
		
        BoolQueryBuilder boolQueryBuilder =  QueryBuilders.boolQuery();
        HighlightBuilder highlightBuilder = new HighlightBuilder();
        
        for(SimpleQueryWithOperatorDTO query : queries) {
            if (query.Field == null || query.Value == null) {
                continue;
            }

            query.Value = query.Value.trim(); 

            if (query.Value.isEmpty()) {
                continue;
            }

            boolQueryBuilder = getQueryBuilder(boolQueryBuilder, query);
            
            if (query.Field.equals(FIRST_NAME_FIELD))
            	highlightBuilder.field(FIRST_NAME_FIELD).preTags(highlightPreTag).postTags(highlightPostTag);
            else if (query.Field.equals(LAST_NAME_FIELD))
            	highlightBuilder.field(LAST_NAME_FIELD).preTags(highlightPreTag).postTags(highlightPostTag);
            else if (query.Field.equals(QUALIFICATION_LEVEL_FIELD))
            	highlightBuilder.field(QUALIFICATION_LEVEL_FIELD).preTags(highlightPreTag).postTags(highlightPostTag);
            else if (query.Field.equals(COVER_LETTER_CONTENT_FIELD))
            	highlightBuilder.field(COVER_LETTER_CONTENT_FIELD).preTags(highlightPreTag).postTags(highlightPostTag);
            else if (query.Field.equals(CV_CONTENT_FIELD))
            	highlightBuilder.field(CV_CONTENT_FIELD).preTags(highlightPreTag).postTags(highlightPostTag);
        }
        
        return resultRetrieveService.getResultWithDynamicHighlight(boolQueryBuilder, highlightBuilder);
	}
	
	private BoolQueryBuilder getQueryBuilder(QueryBuilder boolQueryBuilder,SimpleQueryWithOperatorDTO query) {
		QueryBuilder queryBuilder = null;
        try {
            if (query.Value.startsWith("\"") && query.Value.endsWith("\"")) {
            	query.Value = query.Value.substring(1, query.Value.length()-1);
                queryBuilder =  new QueryFactory().field(query.Field).value(query.Value).buildQuery(SearchType.phrase);
            }
            else {
            	queryBuilder = new QueryFactory().field(query.Field).value(query.Value).buildQuery(SearchType.match);
            }

        } catch (ParseException e) {
            e.printStackTrace();
        }

        BoolQueryBuilder newBoolQueryBuilder = QueryBuilders.boolQuery();

        if (query.Operator == LogicalOperator.NO_OPERATOR) {
            newBoolQueryBuilder.must(queryBuilder);
        }else if(query.Operator == LogicalOperator.AND){
            newBoolQueryBuilder.must(boolQueryBuilder);
            newBoolQueryBuilder.must(queryBuilder);

        }else if(query.Operator == LogicalOperator.OR){
            newBoolQueryBuilder.should(boolQueryBuilder);
            newBoolQueryBuilder.should(queryBuilder);
        }else if(query.Operator == LogicalOperator.AND_NOT){
            newBoolQueryBuilder.must(boolQueryBuilder);
            newBoolQueryBuilder.mustNot(queryBuilder);
        }
        
        return newBoolQueryBuilder;
	}
	
	@Override
	public List<SearchResultDTO> geolocationSearch(UUID cityId, int radius) {
		City city = cityRepository.getById(cityId);
		        
        QueryBuilder queryBuilder = null;
        try {
			queryBuilder =  new QueryFactory().field(GEOLOCATION_FIELD).latitude(city.getLatitude()).longitude(city.getLongitude()).radius(radius).buildQuery(SearchType.geospatial);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			return null;
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
		
        return resultRetrieveService.getGeoSpatialResult(queryBuilder);

	}
}
