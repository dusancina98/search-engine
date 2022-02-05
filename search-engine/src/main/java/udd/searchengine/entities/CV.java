package udd.searchengine.entities;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class CV {

	@Id
    @Column(name = "id")
	private @Setter(AccessLevel.PRIVATE) UUID id;
	
//	@Column(nullable = false)
//	private String content;
	
	@Column(nullable = false)
	private String pathToFIle;
	
	public CV(String pathToFIle) {
		this(UUID.randomUUID(), pathToFIle);
	}
}
