package project.capstone.fick.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.capstone.fick.domain.crack.Crack;
import project.capstone.fick.domain.project.Project;
import project.capstone.fick.domain.project.ProjectRepository;
import project.capstone.fick.domain.user.User;
import project.capstone.fick.domain.user.UserRepository;
import project.capstone.fick.web.dto.crack.CrackListResponseDto;
import project.capstone.fick.web.dto.project.ProjectListResponseDto;
import project.capstone.fick.web.dto.user.UserRequestDto;
import project.capstone.fick.web.dto.user.UserResponseDto;

import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class UserService {

	private final UserRepository userRepository;

	@Transactional(readOnly = true)
	public UserResponseDto.UserId userResponseId(UserRequestDto dto){
		User user = userRepository.findByUIDAndName(dto.getUID(), dto.getName())
			.orElseThrow(IllegalArgumentException::new);
		return new UserResponseDto.UserId(user.getId());
	}

	@Transactional(readOnly = true)
	public UserResponseDto userResponseDto(Long id) {
		User user = userRepository.findUserByJoinFetch(id).orElseThrow(IllegalArgumentException::new);
		return UserResponseDto.builder()
			.user(user)
			.projects(user.getProjectList().stream().map(ProjectListResponseDto::new).collect(Collectors.toList()))
			.build();
	}
}
