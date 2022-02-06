package udd.searchengine;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.util.AnnotatedTypeScanner;

@SpringBootApplication
public class SearchEngineApplication {

	public static void main(String[] args) {
		SpringApplication.run(SearchEngineApplication.class, args);
	}
	
	@Autowired
	ElasticsearchRestTemplate elasticsearchRestTemplate;
	
	@SuppressWarnings("deprecation")
	@PostConstruct
	  public void init() {
	    AnnotatedTypeScanner scanner = new AnnotatedTypeScanner(false, Document.class);
	    for (Class clazz : scanner.findTypes("com.geohash.entity.elastic")) {
	      Document doc = AnnotationUtils.findAnnotation(clazz, Document.class);
	      assert doc != null;
	      if (!elasticsearchRestTemplate.indexExists(doc.indexName())) {
	        elasticsearchRestTemplate.createIndex(clazz);
	      }
	      elasticsearchRestTemplate.refresh(clazz);

	      elasticsearchRestTemplate.putMapping(clazz);
	    }
	}
}
