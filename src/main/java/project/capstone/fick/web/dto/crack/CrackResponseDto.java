package project.capstone.fick.web.dto.crack;

import lombok.Getter;
import project.capstone.fick.domain.Location;
import project.capstone.fick.domain.crack.Crack;
import project.capstone.fick.domain.crack.CrackRiskLevel;

import java.lang.reflect.Field;
import java.time.LocalDateTime;

@Getter
public class CrackResponseDto {

	private Long id;

	private String photoUrl;

	private Double width;

	private Location location;

	private Double height;

	private CrackRiskLevel riskLevel;

	private String comment;

	private Boolean isCrack;

	private LocalDateTime createdDate;

	private Long structureId;

	public CrackResponseDto(Crack crack) {
		this.id = crack.getId();
		this.photoUrl = crack.getPhotoUrl();
		this.width = crack.getWidth();
		this.location = crack.getLocation();
		this.height = crack.getHeight();
		this.riskLevel = crack.getRiskLevel();
		this.comment = crack.getComment();
		this.isCrack = crack.getIsCrack();
		this.structureId = crack.getStructure().getId();
		this.createdDate = crack.getCreatedDate();
	}
}
