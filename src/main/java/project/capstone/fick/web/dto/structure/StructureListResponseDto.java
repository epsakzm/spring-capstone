package project.capstone.fick.web.dto.structure;

import lombok.Getter;
import project.capstone.fick.domain.Location;
import project.capstone.fick.domain.structure.Structure;

import java.time.LocalDateTime;

@Getter
public class StructureListResponseDto {

	private Long id;

	private String name;

	private String comment;

	private String modelUrl;

	private Location location;

	private Double height;

	private LocalDateTime createdDate;

	public StructureListResponseDto(Structure structure) {
		this.id = structure.getId();
		this.name = structure.getName();
		this.comment = structure.getComment();
		this.modelUrl = structure.getModelUrl();
		this.location = Location.builder()
						.locationX(structure.getLocation().getLocationX())
						.locationY(structure.getLocation().getLocationY())
						.locationDetail(structure.getLocation().getLocationDetail())
						.build();
		this.height = structure.getHeight();
		this.createdDate = structure.getCreatedDate();
	}
}
