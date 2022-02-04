package udd.searchengine.repository.elasticsearch;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import udd.searchengine.entities.elasticsearch.CVIndexUnit;

public interface CVElasticSearchRepository extends ElasticsearchRepository<CVIndexUnit, String>{

}
