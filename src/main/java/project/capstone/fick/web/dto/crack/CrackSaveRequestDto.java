package project.capstone.fick.web.dto.crack;

import lombok.*;
import project.capstone.fick.domain.Location;
import project.capstone.fick.domain.crack.Crack;
import project.capstone.fick.domain.crack.CrackRiskLevel;
import project.capstone.fick.domain.structure.Structure;

@Getter
@NoArgsConstructor
public class CrackSaveRequestDto {

	private String photoUrl;

	private Double width;

	private Double locationX;

	private Double locationY;

	private String locationDetail;

	private Double height;

	private Integer riskLevelInteger;

	private String comment;

	private Long structureId;

	public static CrackSaveRequestDto testCrackSaveRequestDto(String photoUrl,
													   Double width) {
		CrackSaveRequestDto dto = new CrackSaveRequestDto();
		dto.photoUrl = photoUrl;
		dto.width = width;
		return dto;
	}

	public Crack toCrackEntity(Structure structure, CrackRiskLevel riskLevel) {
		return Crack.builder()
			.photoUrl(photoUrl)
			.width(width)
			.height(height)
			.comment(comment)
			.location(Location.builder()
						.locationX(locationX)
						.locationY(locationY)
						.locationDetail(locationDetail)
						.build())
			.structure(structure)
			.riskLevel(riskLevel)
			.build();
	}
}
