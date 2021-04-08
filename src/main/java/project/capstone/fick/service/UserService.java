package project.capstone.fick.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.capstone.fick.domain.user.UserRepository;
import project.capstone.fick.web.dto.user.UserRequestDto;
import project.capstone.fick.web.dto.user.UserResponseDto;

@RequiredArgsConstructor
@Service
public class UserService {

	private final UserRepository userRepository;

	@Transactional
	public UserResponseDto userLoginResponse(UserRequestDto dto) {
		return null;
	}
}
