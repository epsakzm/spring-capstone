package project.capstone.fick.security.jwt.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.GenericFilterBean;
import project.capstone.fick.security.SecurityUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public class JwtAuthenticationFilter extends GenericFilterBean {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;

		String jwtTokenValue = resolveJwtTokenValue(request);
		String requestURI = SecurityUtils.resolveRequestURI(request);

	}

	private String resolveJwtTokenValue(ServletRequest request) {
		return SecurityUtils.substringBearerString(((HttpServletRequest)request).getHeader(HttpHeaders.AUTHORIZATION));
	}

}
