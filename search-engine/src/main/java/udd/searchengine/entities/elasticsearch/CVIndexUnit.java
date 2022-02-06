package udd.searchengine.entities.elasticsearch;

import org.elasticsearch.common.geo.GeoPoint;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.elasticsearch.annotations.GeoPointField;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import udd.searchengine.entities.QualificationLevel;

@Document(indexName = CVIndexUnit.INDEX_NAME)
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class CVIndexUnit {

	public static final String INDEX_NAME = "cv_index";
	public static final String TYPE_NAME = "cv";
	
	@Id
	@Field(type = FieldType.Text)
	private String id;
	
	@Field(type = FieldType.Text)
	private String firstName;
	
	@Field(type = FieldType.Text)
	private String lastName;
	
	@Field(type = FieldType.Text)
	private QualificationLevel qualificationLevel;
	
	@Field(type = FieldType.Text)
	private String content;
	
	@GeoPointField
	//@JsonFormat(shape = JsonFormat.Shape.OBJECT) 
	private GeoPoint location;	
	
	@Field(type = FieldType.Text)
	private String city;
}
