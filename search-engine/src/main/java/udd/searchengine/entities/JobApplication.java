package udd.searchengine.entities;

import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class JobApplication {

	@Id
    @Column(name = "id")
	private @Setter(AccessLevel.PRIVATE) UUID id;
	
	@OneToOne(cascade = CascadeType.ALL)
	private Candidate candidate;
	
	@OneToOne(cascade = CascadeType.ALL)
	private CV cvDocument;
	
	@OneToOne(cascade = CascadeType.ALL)
	private CoverLetter coverLetter;

	public JobApplication(Candidate candidate, CV cvDocument, CoverLetter coverLetter) {
		this(UUID.randomUUID(), candidate, cvDocument, coverLetter);
	}
}
