package udd.searchengine.contracts.dto;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class SearchResultDTO extends CandidateInfoDTO{

	public String Highlight;

	public SearchResultDTO(String Highlight, String FirstName, String LastName, String City, String QualificationLevel, double Latitude, double Longitude) {
		super(FirstName, LastName, City, QualificationLevel, Latitude, Longitude);
		this.Highlight = Highlight;
	}
	
	
}
