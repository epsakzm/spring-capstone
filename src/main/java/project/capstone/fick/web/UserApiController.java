package project.capstone.fick.web;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import project.capstone.fick.service.UserService;
import project.capstone.fick.web.dto.user.UserRequestDto;
import project.capstone.fick.web.dto.user.UserResponseDto;

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
	public UserResponseDto userResponseDto(@PathVariable Long id) {
		return userService.userResponseDto(id);
	}
}
