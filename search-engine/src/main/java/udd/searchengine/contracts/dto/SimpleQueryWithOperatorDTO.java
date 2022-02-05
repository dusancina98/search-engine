package udd.searchengine.contracts.dto;

import lombok.NoArgsConstructor;
import udd.searchengine.entities.elasticsearch.LogicalOperator;

@NoArgsConstructor 
public class SimpleQueryWithOperatorDTO extends SimpleQueryDTO{

	public LogicalOperator Operator;

	public SimpleQueryWithOperatorDTO(LogicalOperator operator, String Field, String Value) {
		super(Field, Value);
		Operator = operator;
	}

}
