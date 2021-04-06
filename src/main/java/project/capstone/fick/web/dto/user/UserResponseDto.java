package project.capstone.fick.web.dto.user;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import project.capstone.fick.domain.project.Project;

import java.util.List;

@Getter
@NoArgsConstructor
public class UserResponseDto {

	private Integer UID;
	private String name;
	private List<Project> projectList;

	@Builder
	public UserResponseDto(Integer UID,
						   String name,
						   List<Project> projectList) {
		this.UID = UID;
		this.name = name;
		this.projectList = projectList;
	}
}
