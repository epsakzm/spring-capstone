package project.capstone.fick.web.dto.crack;

import lombok.Getter;
import project.capstone.fick.domain.Location;
import project.capstone.fick.domain.crack.Crack;
import project.capstone.fick.domain.crack.CrackRiskLevel;

import java.time.LocalDateTime;

@Getter
public class CrackListResponseDto {

	private Long id;

	private String comment;

	private String photoUrl;

	private Location location;

	private Double height;

	private Double width;

	private Boolean isCrack;

	private LocalDateTime createdDate;

	private CrackRiskLevel riskLevel;

	public CrackListResponseDto (Crack crack) {
		this.id = crack.getId();
		this.comment = crack.getComment();
		this.photoUrl = crack.getPhotoUrl();
		this.location = Location.builder()
						.locationX(crack.getLocation().getLocationX())
						.locationY(crack.getLocation().getLocationY())
						.locationDetail(crack.getLocation().getLocationDetail())
						.build();
		this.height = crack.getHeight();
		this.riskLevel = crack.getRiskLevel();
		this.createdDate = crack.getCreatedDate();
		this.width = crack.getWidth();
		this.isCrack = crack.getIsCrack();
	}
}
