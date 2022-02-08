package udd.searchengine.services;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import udd.searchengine.contracts.StatisticsService;
import udd.searchengine.contracts.dto.CityRetrieveByLocationDTO;
import udd.searchengine.contracts.dto.FormAccessLocationDTO;
import udd.searchengine.repository.CityRepository;

@Service
public class StatisticsServiceImpl implements StatisticsService{

	private static final Logger logger = LogManager.getLogger(StatisticsServiceImpl.class);

	
	@Autowired
	private CityRepository cityRepository;
	
	@Override
	public void logFormAccessLocation(FormAccessLocationDTO location) {
		List<CityRetrieveByLocationDTO> res = cityRepository.findtCityWithDistance(location.Latitude, location.Longitude);
		CityRetrieveByLocationDTO nearest = getNearestCity(res);
		
		System.out.println(nearest.Id + " " + nearest.Name +" " + nearest.Distance );
		logger.info(String.format("[%s] [%s, %s]", nearest.Name, nearest.Latitude, nearest.Longitude));

		logger.log(Level.getLevel("STATISTICS"), String.format("[%s] [%s, %s]", nearest.Name, nearest.Latitude, nearest.Longitude));

		
	}
	
	private CityRetrieveByLocationDTO getNearestCity(List<CityRetrieveByLocationDTO> res) {
		return Collections.min(res, Comparator.comparing(r -> r.Distance));
	}

}
