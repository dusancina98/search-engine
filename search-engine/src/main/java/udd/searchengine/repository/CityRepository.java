package udd.searchengine.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import udd.searchengine.contracts.dto.CityRetrieveByLocationDTO;
import udd.searchengine.entities.City;

public interface CityRepository extends JpaRepository<City, UUID> {

	String HAVERSINE_FORMULA = "(6371 * acos(cos(radians(:latitude)) * cos(radians(se.latitude)) *" +
							   " cos(radians(se.longitude) - radians(:longitude)) + sin(radians(:latitude)) * sin(radians(se.latitude))))";
	@Query("SELECT new udd.searchengine.contracts.dto.CityRetrieveByLocationDTO(se.id, se.name, se.latitude, se.longitude, " + HAVERSINE_FORMULA + ") FROM City se")
	List<CityRetrieveByLocationDTO> findtCityWithDistance(@Param("latitude") double latitude, @Param("longitude") double longitude);
}
