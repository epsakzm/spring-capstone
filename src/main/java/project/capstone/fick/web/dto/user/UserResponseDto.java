package project.capstone.fick.web.dto.user;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import project.capstone.fick.domain.user.User;
import project.capstone.fick.web.dto.project.ProjectListResponseDto;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class UserResponseDto {

	private Long id;

	private Integer UID;

	private String name;

	private List<ProjectListResponseDto> projects;

	private LocalDateTime createdDate;

	@Builder
	public UserResponseDto(User user,
						   List<ProjectListResponseDto> projects) {
		this.id = user.getId();
		this.UID = user.getUID();
		this.name = user.getName();
		this.createdDate = user.getCreatedDate();
		this.projects = projects;
	}

	@RequiredArgsConstructor
	@Getter
	public static class UserId {
		private final Long id;
	}
}
