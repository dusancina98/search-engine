package udd.searchengine.contracts.dto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor @AllArgsConstructor
public class MostFrequentPartOfDayDTO {

	public String Name;
	
	public String From;
	
	public String To;

	public Long Count;

}
