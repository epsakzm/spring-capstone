package project.capstone.fick.service.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.capstone.fick.security.dto.JwtTokenDto;
import project.capstone.fick.security.jwt.WebTokenProvider;
import project.capstone.fick.web.dto.user.AuthenticateUserRequestDto;

@RequiredArgsConstructor
@Service
public class AuthService {

	private final WebTokenProvider tokenProvider;
	private final AuthenticationManagerBuilder authenticationManagerBuilder;

	@Transactional
	public JwtTokenDto authorize(AuthenticateUserRequestDto requestDto) {
		UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(requestDto.getUsername(), requestDto.getPassword());

		Authentication authenticate = authenticationManagerBuilder.getObject().authenticate(token);

//		SecurityContextHolder.getContext().setAuthentication(authenticate);

		String tokenValue = tokenProvider.createTokenValue(authenticate);
		return new JwtTokenDto(tokenValue);
	}
}
