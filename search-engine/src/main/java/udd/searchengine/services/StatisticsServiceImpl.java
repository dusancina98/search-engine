package udd.searchengine.services;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.aggregations.Aggregation;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.BucketOrder;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;

import udd.searchengine.contracts.StatisticsService;
import udd.searchengine.contracts.dto.CityRetrieveByLocationDTO;
import udd.searchengine.contracts.dto.FormAccessLocationDTO;
import udd.searchengine.contracts.dto.MostFrequentCityDTO;
import udd.searchengine.contracts.dto.MostFrequentPartOfDayDTO;
import udd.searchengine.contracts.dto.StatisticsDTO;
import udd.searchengine.entities.elasticsearch.LogIndex;
import udd.searchengine.repository.CityRepository;
import udd.searchengine.services.util.elasticsearch.PartsOfDay;
import udd.searchengine.services.util.elasticsearch.StatisticsConstants;

@Service
public class StatisticsServiceImpl implements StatisticsService{

	private static final Logger logger = LogManager.getLogger(StatisticsServiceImpl.class);

	@Autowired
	private ElasticsearchRestTemplate elasticsearchRestTemplate;
	

	@Autowired
	private CityRepository cityRepository;
	
	
	@Override
	public void logFormAccessLocation(FormAccessLocationDTO location) {
		List<CityRetrieveByLocationDTO> res = cityRepository.findtCityWithDistance(location.Latitude, location.Longitude);
		CityRetrieveByLocationDTO nearest = getNearestCity(res);
		
		System.out.println(nearest.Id + " " + nearest.Name +" " + nearest.Distance );
		logger.info(String.format("[%s] [%s, %s]", nearest.Name, nearest.Latitude, nearest.Longitude));

		logger.log(Level.getLevel("STATISTICS"), String.format("[%s] [%s, %s]", nearest.Name, nearest.Latitude, nearest.Longitude));

		
	}
	
	private CityRetrieveByLocationDTO getNearestCity(List<CityRetrieveByLocationDTO> res) {
		return Collections.min(res, Comparator.comparing(r -> r.Distance));
	}

	@Override
	public StatisticsDTO getStatistics() {
		return new StatisticsDTO(getMostFrequentCity(), getMostFrequentPartOfDay());
	}
	
	private MostFrequentPartOfDayDTO getMostFrequentPartOfDay() {
		long maxHits = 0;
		MostFrequentPartOfDayDTO mostFrequentPartOfDay = null;

		for (String partOfDay : PartsOfDay.values.keySet()) {
			Map<String, String> params = PartsOfDay.values.get(partOfDay);
			
			QueryBuilder queryBuilder = QueryBuilders.boolQuery()  
			          					.must(QueryBuilders.rangeQuery(StatisticsConstants.TIME_FIELD) 
			          					.gte(params.get("gte"))  
			          					.lt(params.get("lt")));
			
			NativeSearchQuery searchQuery = new NativeSearchQueryBuilder()
		            								.withQuery(queryBuilder)
		            								.build();
			
			SearchHits<LogIndex> searchHits = elasticsearchRestTemplate.search(searchQuery, LogIndex.class);
			if (searchHits == null)
				continue;
			
			if (searchHits.getTotalHits() > maxHits) {
				maxHits = searchHits.getTotalHits();
				mostFrequentPartOfDay = new MostFrequentPartOfDayDTO(partOfDay, params.get("from"), params.get("to"), maxHits);
			}
		}
		return mostFrequentPartOfDay;
	}
	
	private MostFrequentCityDTO getMostFrequentCity() {
		NativeSearchQuery searchQuer1y =  new NativeSearchQueryBuilder()
												.addAggregation(AggregationBuilders.terms(StatisticsConstants.AGGREGATION_NAME)
											    .field(StatisticsConstants.AGGREGATION_FIELD).order(BucketOrder.count(false)).size(1))
												.build();


		Aggregations searchHitss = elasticsearchRestTemplate.search(searchQuer1y, LogIndex.class).getAggregations();
		Aggregation top_city = searchHitss.getAsMap().get(StatisticsConstants.AGGREGATION_NAME);
		
		if (top_city != null) {
			List<? extends Terms.Bucket> buckets = ((Terms) top_city).getBuckets();
			if (buckets != null) {
				if (buckets.size() > 0) {
					Terms.Bucket bucket = buckets.get(0);
					return new MostFrequentCityDTO(bucket.getKeyAsString(), bucket.getDocCount());
				}
			} 
		}
		return null;
	}

}
