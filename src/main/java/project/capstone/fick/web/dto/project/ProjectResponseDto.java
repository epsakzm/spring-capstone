package project.capstone.fick.web.dto.project;

import lombok.Builder;
import lombok.Getter;
import project.capstone.fick.domain.Location;
import project.capstone.fick.domain.project.Project;
import project.capstone.fick.web.dto.structure.StructureListResponseDto;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class ProjectResponseDto {

	private Long id;

	private String name;

	private Location location;

	private String photoUrl;

	private String comment;

	private Long userId;

	private LocalDateTime createdDate;

	private List<StructureListResponseDto> structures;

	@Builder
	public ProjectResponseDto(Project project,
							  List<StructureListResponseDto> structures) {
		this.id = project.getId();
		this.name = project.getName();
		this.location = project.getLocation();
		this.photoUrl = project.getPhotoUrl();
		this.comment = project.getComment();
		this.userId = project.getUser().getId();
		this.createdDate = project.getCreatedDate();
		this.structures = structures;
	}

}
