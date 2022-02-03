package udd.searchengine.contracts.dto;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import udd.searchengine.entities.QualificationLevel;

@NoArgsConstructor @AllArgsConstructor
public class ApplicationCandidateDTO {
	
	public String FirstName;
    
	public String LastName;
	
	public QualificationLevel QualificationLevel;
	
	public UUID CityId;
	
}
