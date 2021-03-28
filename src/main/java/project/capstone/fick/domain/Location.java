package project.capstone.fick.domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Location {

	private Double x;

	private Double y;

	@Column(nullable = true)
	private String description;
}
