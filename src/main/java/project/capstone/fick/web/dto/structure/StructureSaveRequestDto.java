package project.capstone.fick.web.dto.structure;

import lombok.Getter;
import lombok.NoArgsConstructor;
import project.capstone.fick.domain.Location;
import project.capstone.fick.domain.project.Project;
import project.capstone.fick.domain.structure.Structure;

@Getter
@NoArgsConstructor
public class StructureSaveRequestDto {

	private String name;

	private String comment;

	private Double height;

	private Boolean isWorkDone;

	private String modelUrl;

	private Double locationX;

	private Double locationY;

	private String locationDetail;

	private Long projectId;

	public Structure toStructureEntity(Project project) {
		return Structure.builder()
			.name(name)
			.comment(comment)
			.height(height)
			.isWorkDone(isWorkDone)
			.modelUrl(modelUrl)
			.location(Location.builder()
				.locationX(locationX)
				.locationY(locationY)
				.locationDetail(locationDetail)
				.build())
			.project(project)
			.build();
	}
}
