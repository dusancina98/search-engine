package udd.searchengine.contracts;

import udd.searchengine.contracts.dto.FormAccessLocationDTO;

public interface StatisticsService {

	void logFormAccessLocation(FormAccessLocationDTO location);
}
