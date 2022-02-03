package udd.searchengine.contracts;

import java.io.IOException;
import java.util.Collection;
import java.util.UUID;

import udd.searchengine.contracts.dto.CityResponseDTO;
import udd.searchengine.contracts.dto.JobApplicationDTO;

public interface JobAplicationService {

	Collection<CityResponseDTO> findAllCities();
	
	UUID create(JobApplicationDTO jobApplicationDTO) throws IOException;
}
