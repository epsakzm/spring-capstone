package project.capstone.fick.web.dto.project;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import project.capstone.fick.domain.Location;
import project.capstone.fick.domain.project.Project;

import java.time.LocalDateTime;

@Slf4j
@Getter
public class ProjectListResponseDto {

	private Long id;

	private String name;

	private String comment;

	private Location location;

	private LocalDateTime createdDate;

	public ProjectListResponseDto (Project project) {
		this.id = project.getId();
		this.name = project.getName();
		this.comment = project.getComment();
		this.location = Location.builder()
						.locationX(project.getLocation().getLocationX())
						.locationY(project.getLocation().getLocationY())
						.locationDetail(project.getLocation().getLocationDetail())
						.build();
		this.createdDate = project.getCreatedDate();
	}
}
