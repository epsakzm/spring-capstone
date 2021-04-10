package project.capstone.fick.web.dto.project;

import lombok.Getter;
import lombok.NoArgsConstructor;
import project.capstone.fick.domain.Location;
import project.capstone.fick.domain.project.Project;
import project.capstone.fick.domain.user.User;

@Getter
@NoArgsConstructor
public class ProjectSaveRequestDto {

	private String name;

	private String comment;

	private Double locationX;

	private Double locationY;

	private String locationDetail;

	private String photoUrl;

	private Long userId;

	public Project toProjectEntity (User user) {
		return Project.builder()
			.name(name)
			.comment(comment)
			.location(Location.builder()
				.locationX(locationX)
				.locationY(locationY)
				.locationDetail(locationDetail)
				.build())
			.photoUrl(photoUrl)
			.user(user)
			.build();
	}
}
