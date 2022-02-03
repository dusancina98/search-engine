package udd.searchengine.services;

import java.io.IOException;
import java.util.Collection;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import udd.searchengine.contracts.FileUtilService;
import udd.searchengine.contracts.JobAplicationService;
import udd.searchengine.contracts.dto.CityResponseDTO;
import udd.searchengine.contracts.dto.JobApplicationDTO;
import udd.searchengine.entities.CV;
import udd.searchengine.entities.CoverLetter;
import udd.searchengine.entities.JobApplication;
import udd.searchengine.repository.CityRepository;
import udd.searchengine.repository.JobApplicationRepository;
import udd.searchengine.services.util.CityMapper;
import udd.searchengine.services.util.JobApplicationMapper;

@Service
public class JobApplicationServiceImpl implements JobAplicationService{

    private static final String CV_DOCUMENTS_FOLDER_PATH = "/cv-documents";
	
    private static final String COVER_LETTER_DOCUMENTS_FOLDER_PATH = "/cover-letter-documents";

	@Autowired
	private CityRepository cityRepository;
	
	@Autowired
	private JobApplicationRepository jobApplicationRepository;
	
	@Autowired
	private FileUtilService fileUtilService;
	
	@Override
	public Collection<CityResponseDTO> findAllCities() {
		return CityMapper.mapCityCollectionToCityResponseDTOCollection(cityRepository.findAll());
	}

	@Override
	public UUID create(JobApplicationDTO jobApplicationDTO) throws IOException {
		JobApplication jobApplication = JobApplicationMapper.mapJobApplicationDTOToJobApplication(jobApplicationDTO, cityRepository.getById(jobApplicationDTO.ApplicationCandidate.CityId));
				
		try {
			String cvPath = fileUtilService.saveFileAndGetPath(jobApplicationDTO.CVDocument, jobApplication.getId().toString(), CV_DOCUMENTS_FOLDER_PATH);
			jobApplication.setCvDocument(new CV("", cvPath));
			
			String coverLetterPath = fileUtilService.saveFileAndGetPath(jobApplicationDTO.CoverLetterDocument, jobApplication.getId().toString(), COVER_LETTER_DOCUMENTS_FOLDER_PATH);
			jobApplication.setCoverLetter(new CoverLetter("", coverLetterPath));
		} catch (IOException e) {
			e.printStackTrace();
			throw e;
		}
		
		jobApplicationRepository.save(jobApplication);
		return jobApplication.getId();
	}

}
