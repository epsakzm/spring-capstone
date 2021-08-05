package project.capstone.fick.security.config;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import project.capstone.fick.security.domain.Role;
import project.capstone.fick.security.handler.JwtAccessDeniedHandler;
import project.capstone.fick.security.handler.JwtAuthenticationEntryPoint;
import project.capstone.fick.security.jwt.JwtSecurityConfigAdapter;
import project.capstone.fick.security.jwt.WebTokenProvider;

@RequiredArgsConstructor
@EnableWebSecurity
//@EnableGlobalMethodSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	private final JwtAccessDeniedHandler jwtAccessDeniedHandler;
	private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
	private final WebTokenProvider tokenProvider;

	@Override
	public void configure(WebSecurity web) throws Exception {
		web
			.ignoring()
			.requestMatchers(PathRequest.toStaticResources().atCommonLocations())
			.antMatchers("/h2-console/**");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.csrf()
				.disable()

			.headers()
				.frameOptions()
					.sameOrigin()
		.and()
			.exceptionHandling()
				.authenticationEntryPoint(jwtAuthenticationEntryPoint)
				.accessDeniedHandler(jwtAccessDeniedHandler)

		.and()
			.sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS)

		.and()
			.authorizeRequests()
				.antMatchers(HttpMethod.POST, "/api/v1/auth")
					.permitAll()
				.anyRequest()
					.authenticated()

		.and()
			.apply(new JwtSecurityConfigAdapter(tokenProvider));
	}
}
