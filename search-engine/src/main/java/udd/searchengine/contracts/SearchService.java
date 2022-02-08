package udd.searchengine.contracts;

import java.util.List;
import java.util.UUID;

import udd.searchengine.contracts.dto.SearchResultDTO;
import udd.searchengine.contracts.dto.SimpleQueryWithOperatorDTO;

public interface SearchService extends SearchConfigConstants{
	
	List<SearchResultDTO> search(List<SimpleQueryWithOperatorDTO> queries);
	
	List<SearchResultDTO> geolocationSearch(UUID cityId, int radius);
}
