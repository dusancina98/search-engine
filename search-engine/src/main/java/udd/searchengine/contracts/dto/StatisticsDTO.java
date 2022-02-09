package udd.searchengine.contracts.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor @AllArgsConstructor
public class StatisticsDTO {
	
	public MostFrequentCityDTO MostFrequentCity;
	
	public MostFrequentPartOfDayDTO MostFrequentPartOfTheDay;
	
	public List<MostFrequentCityDTO> Cities;
	
	public List<MostFrequentPartOfDayDTO> PartsOfDay;

}
