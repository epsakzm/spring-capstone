package project.capstone.fick.security.config;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import project.capstone.fick.security.domain.Role;

@EnableWebSecurity
//@EnableGlobalMethodSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth
			.inMemoryAuthentication()
			.withUser("admin")
			.password("{noop}1234")
			.authorities(Role.ADMIN.getKey());
	}

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
			.authorizeRequests()
				.anyRequest()
					.permitAll();

	}
}
