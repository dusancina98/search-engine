package udd.searchengine.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import udd.searchengine.entities.JobApplication;

public interface JobApplicationRepository extends JpaRepository<JobApplication, UUID>{

}
