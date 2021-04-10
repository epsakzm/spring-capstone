package project.capstone.fick.web.dto.user;

import lombok.Builder;
import lombok.Getter;
import project.capstone.fick.domain.user.User;
import project.capstone.fick.web.dto.project.ProjectListResponseDto;

import java.util.List;

@Getter
public class UserResponseDto {

	private Long id;
	private Integer UID;
	private String name;
	private List<ProjectListResponseDto> projects;

	@Builder
	public UserResponseDto(User user,
						   List<ProjectListResponseDto> projects) {
		this.id = user.getId();
		this.UID = user.getUID();
		this.name = user.getName();
		this.projects = projects;
	}

}
