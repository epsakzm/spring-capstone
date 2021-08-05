package project.capstone.fick.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import project.capstone.fick.security.SecurityUtils;
import project.capstone.fick.security.dto.JwtTokenDto;
import project.capstone.fick.service.security.AuthService;
import project.capstone.fick.web.dto.user.AuthenticateUserRequestDto;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
public class AuthApiController {

	private final AuthService authService;

	@PostMapping("/api/v1/auth")
	public ResponseEntity<JwtTokenDto> authorize(@Valid @RequestBody AuthenticateUserRequestDto requestDto) {
		JwtTokenDto tokenDto = authService.authorize(requestDto);
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.add(HttpHeaders.AUTHORIZATION, SecurityUtils.BEARER + tokenDto.getTokenValue());

		return new ResponseEntity<>(tokenDto, httpHeaders, HttpStatus.OK);
	}
}
