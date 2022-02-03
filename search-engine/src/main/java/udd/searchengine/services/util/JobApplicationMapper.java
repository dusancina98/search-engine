package udd.searchengine.services.util;

import udd.searchengine.contracts.dto.JobApplicationDTO;
import udd.searchengine.entities.Candidate;
import udd.searchengine.entities.City;
import udd.searchengine.entities.JobApplication;

public class JobApplicationMapper {

	public static JobApplication mapJobApplicationDTOToJobApplication(JobApplicationDTO jobApplicationDTO, City city) {
		return new JobApplication(new Candidate(jobApplicationDTO.ApplicationCandidate.FirstName,
												jobApplicationDTO.ApplicationCandidate.LastName,
												city,
												jobApplicationDTO.ApplicationCandidate.QualificationLevel),
								  null, null);
	}
}
