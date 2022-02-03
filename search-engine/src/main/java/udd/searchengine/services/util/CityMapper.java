package udd.searchengine.services.util;

import java.util.Collection;
import java.util.stream.Collectors;

import udd.searchengine.contracts.dto.CityResponseDTO;
import udd.searchengine.entities.City;

public class CityMapper {
	
	public static Collection<CityResponseDTO> mapCityCollectionToCityResponseDTOCollection(Collection<City> cities) {
		return cities.stream().map(city -> mapCityToCityResponseDTO(city)).collect(Collectors.toList());
	}

	private static CityResponseDTO mapCityToCityResponseDTO(City city) {
		return new CityResponseDTO(city.getId(), city.getName());
	}
}
