package udd.searchengine.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import udd.searchengine.entities.City;

public interface CityRepository extends JpaRepository<City, UUID> {

}
