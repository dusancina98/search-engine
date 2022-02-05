package udd.searchengine.services.util.elasticsearch;

import org.apache.lucene.queryparser.classic.ParseException;
import org.elasticsearch.common.unit.DistanceUnit;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;

import lombok.NoArgsConstructor;
import udd.searchengine.entities.elasticsearch.SearchType;

@NoArgsConstructor
public class QueryFactory {
	
	private double latitude = 0;
	
	private double longitude = 0;

	private int radius = 0;

	
	public QueryFactory latitude(double latitude){
		this.latitude = latitude;
		return this;
	}
	
	public QueryFactory longitude(double longitude){
		this.longitude = longitude;
		return this;
	}
	
	public QueryFactory radius(int radius){
		this.radius = radius;
		return this;
	}

	
	public QueryBuilder buildQuery(SearchType queryType, String field, String value) throws IllegalArgumentException, ParseException{
		String errorMessage = "";
		if(field == null || field.equals("")){
			errorMessage += "Field not specified";
		}
		if(value == null){
			if(!errorMessage.equals("")) errorMessage += "\n";
			errorMessage += "Value not specified";
		}
		if(!errorMessage.equals("")){
			throw new IllegalArgumentException(errorMessage);
		}
		
		QueryBuilder retVal = null;
		if(queryType.equals(SearchType.regular)) {
			// Znaci ovaj tip pretrage ce vratiti nesto jedino u slucaju kad u upitu navedemo
			// rec koja se u potpunosti poklapa sa nekim od term-ova iz index-a
			retVal = QueryBuilders.termQuery(field, value);
		} else if(queryType.equals(SearchType.match)) {
			// To search text field values, use the match query instead term query.
			// https://www.elastic.co/guide/en/elasticsearch/reference/current/query-dsl-term-query.html
			// Za ovaj tip pretrage, isto preprocesiranje se izvrsava i prilikom indeksiranja i prilikom pretrage
			retVal = QueryBuilders.matchQuery(field, value);
		} else if(queryType.equals(SearchType.prefix)){
			retVal = QueryBuilders.prefixQuery(field, value);
		} else if(queryType.equals(SearchType.range)){
			String[] values = value.split(" ");
			retVal = QueryBuilders.rangeQuery(field).from(values[0]).to(values[1]);
		} else if(queryType.equals(SearchType.geospatial)){
			retVal = QueryBuilders.geoDistanceQuery(field)
									.point(latitude, longitude)
									.distance(radius, DistanceUnit.KILOMETERS);
		}else{
			retVal = QueryBuilders.matchPhraseQuery(field, value);
		}

		return retVal;
	}

}
