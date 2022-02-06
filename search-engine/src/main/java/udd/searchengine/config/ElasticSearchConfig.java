package udd.searchengine.config;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.IndexOperations;

import udd.searchengine.entities.elasticsearch.CVIndexUnit;

@Configuration
public class ElasticSearchConfig {
	
	@Autowired
	private ElasticsearchRestTemplate elasticsearchRestTemplate;

	@PostConstruct
	public void init() {
	    IndexOperations indexOperations = elasticsearchRestTemplate.indexOps(CVIndexUnit.class);
	    indexOperations.putMapping(indexOperations.createMapping());
	    indexOperations.refresh();
	}
}
