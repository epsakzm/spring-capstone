package project.capstone.fick.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Embeddable
public class Location {

	private Double locationX;

	private Double locationY;

	private String locationDetail;

	@Builder
	public Location(Double locationX,
					Double locationY,
					String locationDetail) {
		this.locationX = locationX;
		this.locationY = locationY;
		this.locationDetail = locationDetail;
	}
}
