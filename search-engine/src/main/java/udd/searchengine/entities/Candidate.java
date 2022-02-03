package udd.searchengine.entities;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Candidate {

	@Id
    @Column(name = "id")
	private @Setter(AccessLevel.PRIVATE) UUID id;
	
	@Column(nullable = false)
	private String firstNname;
	
	@Column(nullable = false)
	private String lastNname;
	
	@ManyToOne
	private City city;
	
    @Enumerated(EnumType.STRING)
	private QualificationLevel qualificationLevel;
	
	public Candidate(String firstName, String lastName, City city, QualificationLevel qualificationLevel) {
		this(UUID.randomUUID(), firstName, lastName, city, qualificationLevel);
	}
}
