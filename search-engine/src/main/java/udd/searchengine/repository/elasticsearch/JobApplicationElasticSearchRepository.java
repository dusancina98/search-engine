package udd.searchengine.repository.elasticsearch;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import udd.searchengine.entities.elasticsearch.IndexUnit;

public interface JobApplicationElasticSearchRepository extends ElasticsearchRepository<IndexUnit, String>{

}
