package udd.searchengine.contracts;

import java.util.List;

import udd.searchengine.contracts.dto.SearchResultDTO;
import udd.searchengine.contracts.dto.SimpleQueryWithOperatorDTO;

public interface SearchService {

	List<SearchResultDTO> search(List<SimpleQueryWithOperatorDTO> queries);
}
