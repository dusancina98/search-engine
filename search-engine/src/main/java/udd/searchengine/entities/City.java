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

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
@Entity
public class City {

	@Id
    @Column(name = "id")
	private @Setter(AccessLevel.PRIVATE) UUID id;
	
	@Column(nullable = false)
	private String name;
	
	private double latitude;
	
	private double longitude;
}
