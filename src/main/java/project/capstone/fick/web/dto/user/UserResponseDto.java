package project.capstone.fick.web.dto.user;

import lombok.Builder;
import lombok.Getter;
import project.capstone.fick.domain.user.User;
import project.capstone.fick.web.dto.project.ProjectResponseDto;

import java.util.List;

@Getter
public class UserResponseDto {

	private Long id;
	private Integer UID;
	private String name;
	private List<ProjectResponseDto> projectList;

	@Builder
	public UserResponseDto(User user,
						   List<ProjectResponseDto> projectList) {
		this.id = user.getId();
		this.UID = user.getUID();
		this.name = user.getName();
		this.projectList = projectList;
	}

}
