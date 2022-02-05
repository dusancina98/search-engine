package udd.searchengine.services;

import java.util.List;

import org.apache.lucene.queryparser.classic.ParseException;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import udd.searchengine.contracts.ResultRetrieveService;
import udd.searchengine.contracts.SearchService;
import udd.searchengine.contracts.dto.SearchResultDTO;
import udd.searchengine.contracts.dto.SimpleQueryWithOperatorDTO;
import udd.searchengine.entities.elasticsearch.LogicalOperator;
import udd.searchengine.entities.elasticsearch.SearchType;
import udd.searchengine.services.util.elasticsearch.QueryFactory;

@Service
public class SearchServiceImpl implements SearchService{

	@Autowired
	private ResultRetrieveService resultRetrieveService;
	
	@Override
	public List<SearchResultDTO> search(List<SimpleQueryWithOperatorDTO> queries) {
		
        BoolQueryBuilder boolQueryBuilder =  QueryBuilders.boolQuery();
        for(SimpleQueryWithOperatorDTO query : queries) {
            if (query.Field == null || query.Value == null) {
                continue;
            }

            query.Value = query.Value.trim(); 

            if (query.Value.isEmpty()) {
                continue;
            }

            QueryBuilder queryBuilder = null;
            try {
                if (query.Value.startsWith("\"") && query.Value.endsWith("\"")) {
                	query.Value = query.Value.substring(1, query.Value.length()-1);
                    queryBuilder =  new QueryFactory().buildQuery(SearchType.phrase, query.Field, query.Value);
                }
                else {
                	queryBuilder = new QueryFactory().buildQuery(SearchType.match, query.Field, query.Value);
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

            boolQueryBuilder = newBoolQueryBuilder;
        }
        
        return resultRetrieveService.getResultWithDynamicHighlight(boolQueryBuilder);
	}

}
