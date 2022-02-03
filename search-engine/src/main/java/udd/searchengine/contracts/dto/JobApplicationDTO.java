package udd.searchengine.contracts.dto;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor @AllArgsConstructor
public class JobApplicationDTO {

	public ApplicationCandidateDTO ApplicationCandidate;
  	
	public MultipartFile CVDocument;

	public MultipartFile CoverLetterDocument;
}
