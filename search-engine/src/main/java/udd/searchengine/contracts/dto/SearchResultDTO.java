package udd.searchengine.contracts.dto;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class SearchResultDTO extends CandidateInfoDTO{
	
	public String Id;
	
	public String Highlight;

	public SearchResultDTO(String Id, String Highlight, String FirstName, String LastName, String City, String QualificationLevel, double Latitude, double Longitude) {
		super(FirstName, LastName, City, QualificationLevel, Latitude, Longitude);
		this.Id = Id;
		this.Highlight = Highlight;
	}
	
	
}
