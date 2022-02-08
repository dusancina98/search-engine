package udd.searchengine.contracts;

import java.util.List;

import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;

import udd.searchengine.contracts.dto.SearchResultDTO;

public interface ResultRetrieveService extends SearchConfigConstants{

	List<SearchResultDTO> getResultWithDynamicHighlight(QueryBuilder queryBuilder, HighlightBuilder highlightBuilder);
	
	List<SearchResultDTO> getGeoSpatialResult(QueryBuilder queryBuilder);
}
