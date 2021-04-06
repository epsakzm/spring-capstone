package project.capstone.fick.web;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import project.capstone.fick.service.UserService;
import project.capstone.fick.web.dto.user.UserRequestDto;
import project.capstone.fick.web.dto.user.UserResponseDto;

import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@RestController
public class UserApiController {

	private final UserService userService;

	@PostMapping("/api/v1/user")
	public UserResponseDto userLoginResponse(@RequestBody UserRequestDto dto) {
		return userService.userLoginResponse(dto);
	}

}
