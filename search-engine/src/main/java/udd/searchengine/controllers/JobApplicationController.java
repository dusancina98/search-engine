package udd.searchengine.controllers;

import java.util.EnumSet;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;

import udd.searchengine.contracts.JobAplicationService;
import udd.searchengine.contracts.dto.ApplicationCandidateDTO;
import udd.searchengine.contracts.dto.JobApplicationDTO;
import udd.searchengine.contracts.dto.QualificationLevelDTO;
import udd.searchengine.entities.QualificationLevel;

@RestController
@RequestMapping(value = "api/job-application")
public class JobApplicationController {

	@Autowired
	private JobAplicationService jobAplicationService;
	
	@GetMapping("/cities")
	@CrossOrigin
	public ResponseEntity<?> findAllCities(){
		try {
			return new ResponseEntity<>(jobAplicationService.findAllCities(),HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/qualification-levels")
	@CrossOrigin
	public ResponseEntity<?> findAllQualificationLevels(){
		try {
			
			List<QualificationLevelDTO> enumNames = EnumSet.allOf(QualificationLevel.class).stream()
															.map(e -> new  QualificationLevelDTO(e.name(), e.name()))
															.collect(Collectors.toList());
			
			return new ResponseEntity<>(enumNames,HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping
	@CrossOrigin
	public ResponseEntity<?> applyForJob(@RequestParam("CoverLetterDocument") MultipartFile coverLetterDocument,
										 @RequestParam("CVDocument") MultipartFile cvDocument,
										 @RequestParam("ApplicationCandidate") String applicationCandidate){
		try {
			ObjectMapper mapper = new ObjectMapper();
			ApplicationCandidateDTO applicationCandidateDTO = mapper.readValue(applicationCandidate, ApplicationCandidateDTO.class);
			JobApplicationDTO jobApplication = new JobApplicationDTO(applicationCandidateDTO, cvDocument, coverLetterDocument);
			
			return new ResponseEntity<>(jobAplicationService.create(jobApplication), HttpStatus.CREATED);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
