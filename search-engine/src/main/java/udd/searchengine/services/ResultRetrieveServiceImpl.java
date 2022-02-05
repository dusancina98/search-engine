package udd.searchengine.services;

import java.util.ArrayList;
import java.util.List;

import org.elasticsearch.index.query.QueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import udd.searchengine.contracts.ResultRetrieveService;
import udd.searchengine.contracts.dto.SearchResultDTO;
import udd.searchengine.entities.elasticsearch.CVIndexUnit;
import udd.searchengine.repository.elasticsearch.CVElasticSearchRepository;

@Service
public class ResultRetrieveServiceImpl implements ResultRetrieveService{

	@Autowired
	private CVElasticSearchRepository cvElasticSearchRepository;
	
	@SuppressWarnings("deprecation")
	@Override
	public List<SearchResultDTO> getResultWithDynamicHighlight(QueryBuilder queryBuilder) {
		if (queryBuilder == null) {
			return null;
		}

		List<SearchResultDTO> results = new ArrayList<SearchResultDTO>();
       
        for (CVIndexUnit indexUnit : cvElasticSearchRepository.search(queryBuilder)) {
        	String highlight = "";
        	//create dynamic highlight
        	results.add(new SearchResultDTO(indexUnit.getId() ,highlight, indexUnit.getFirstName(), indexUnit.getLastName(), indexUnit.getCity(), indexUnit.getQualificationLevel().name(), indexUnit.getLocation().getLat(), indexUnit.getLocation().getLon()));
		}
        
		
		return results;
	}

}
