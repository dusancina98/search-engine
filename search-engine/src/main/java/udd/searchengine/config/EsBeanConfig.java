package udd.searchengine.config;

import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.RestClients;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@ConfigurationProperties(prefix = "elastic")
@EnableElasticsearchRepositories(basePackages = "udd.searchengine.repository.elasticsearch")
@Configuration
@Setter
@Getter
@NoArgsConstructor
public class EsBeanConfig {

	 @Value("${elasticsearch.host}")
	 private String elasticsearchHost;

	 @Value("${elasticsearch.port}")
	 private int elasticsearchPort;

	 @Bean
	 public RestHighLevelClient client() {
		 ClientConfiguration clientConfiguration = ClientConfiguration.builder()
				 .connectedTo(elasticsearchHost + ":" + elasticsearchPort)
				 .build();

		 return RestClients.create(clientConfiguration).rest();
	 }

	 @Bean(name = "elasticsearchRestTemplate")
	 public ElasticsearchRestTemplate elasticsearchRestTemplate() {
		 return new ElasticsearchRestTemplate(client());
	 }

}
