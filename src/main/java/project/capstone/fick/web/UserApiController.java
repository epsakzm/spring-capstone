package project.capstone.fick.web;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project.capstone.fick.service.UserService;
import project.capstone.fick.web.dto.user.UserRequestDto;
import project.capstone.fick.web.dto.user.UserResponseDto;

@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
@RestController
public class UserApiController {

	private final UserService userService;

	@PostMapping
	public UserResponseDto userLoginResponse(@RequestBody UserRequestDto dto) {
		return userService.userLoginResponse(dto);
	}

}
