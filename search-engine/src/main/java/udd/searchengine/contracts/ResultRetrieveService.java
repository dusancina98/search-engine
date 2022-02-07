package udd.searchengine.contracts;

import java.util.List;

import org.elasticsearch.index.query.QueryBuilder;

import udd.searchengine.contracts.dto.SearchResultDTO;

public interface ResultRetrieveService {

	List<SearchResultDTO> getResultWithDynamicHighlight(QueryBuilder queryBuilder, String valueForDynamicHighlight);
	
	List<SearchResultDTO> getGeoSpatialResult(QueryBuilder queryBuilder);
}
