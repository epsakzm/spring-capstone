package project.capstone.fick.security.config;

import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import project.capstone.fick.security.jwt.WebTokenProvider;
import project.capstone.fick.security.jwt.filter.JwtAuthenticationFilter;

@RequiredArgsConstructor
public class JwtSecurityConfigAdapter extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

	private final WebTokenProvider tokenProvider;

	@Override
	public void configure(HttpSecurity httpSecurity) throws Exception {
		httpSecurity
			.addFilterBefore(new JwtAuthenticationFilter(tokenProvider), UsernamePasswordAuthenticationFilter.class);
	}

}
