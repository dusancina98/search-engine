package udd.searchengine.contracts.dto;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor @AllArgsConstructor
public class CityRetrieveByLocationDTO {
	
	public UUID Id;

	public String Name;

	public Double Latitude;

	public Double Longitude;
	
	public Double Distance;

}
