package udd.searchengine.contracts;

import udd.searchengine.contracts.dto.FormAccessLocationDTO;
import udd.searchengine.contracts.dto.StatisticsDTO;

public interface StatisticsService {

	void logFormAccessLocation(FormAccessLocationDTO location);
	
	StatisticsDTO getStatistics();

}
