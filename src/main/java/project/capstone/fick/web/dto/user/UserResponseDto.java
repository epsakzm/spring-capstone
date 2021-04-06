package project.capstone.fick.web.dto.user;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import project.capstone.fick.domain.project.Project;
import project.capstone.fick.domain.user.User;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
public class UserResponseDto {

	private Integer UID;
	private String name;
	private List<Long> projectIdList;

	@Builder
	public UserResponseDto(User user) {
		this.UID = user.getUID();
		this.name = user.getName();
		this.projectIdList = user.getProjectList().stream().map(Project::getId).collect(Collectors.toList());
	}

}
