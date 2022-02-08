package udd.searchengine.entities.elasticsearch;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Document(indexName = LogIndex.INDEX_NAME, shards = 1, replicas = 0)
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class LogIndex {
	public static final String INDEX_NAME = "search-engine-stats";
	
	@Id
	@Field(type = FieldType.Text)
	private String id;
}
