package project.capstone.fick.web;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import project.capstone.fick.service.UserService;
import project.capstone.fick.web.dto.project.ProjectListResponseDto;
import project.capstone.fick.web.dto.user.UserRequestDto;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
@RestController
public class UserApiController {

	private final UserService userService;

	@PostMapping
	public Long userResponse(@RequestBody UserRequestDto dto){
		return userService.userResponse(dto);
	}

	@GetMapping("/{id}")
	public List<ProjectListResponseDto> userProjectListResponse(@PathVariable Long id) {
		return userService.userProjectListResponseById(id);
	}
}
