package project.capstone.fick.domain;

import javax.persistence.Embeddable;

@Embeddable
public class Location {

	private Double locationX;

	private Double locationY;

	private String locationDetail;
}
