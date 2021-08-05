package project.capstone.fick.security.jwt.filter;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.GenericFilterBean;
import project.capstone.fick.security.SecurityUtils;
import project.capstone.fick.security.jwt.WebTokenProvider;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@RequiredArgsConstructor
@Slf4j
public class JwtAuthenticationFilter extends GenericFilterBean {

	private final WebTokenProvider tokenProvider;

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;

		String jwtTokenValue = resolveJwtTokenByRequestHeader(request);

		if (StringUtils.hasText(jwtTokenValue) && tokenProvider.validateToken(jwtTokenValue)) {
				SecurityContextHolder.getContext()
					.setAuthentication(tokenProvider.getAuthenticationByTokenValue(jwtTokenValue));
				log.info("Security Context : 인증 정보 저장됨 (Request URI : {})", ((HttpServletRequest) request).getRequestURI());
		} else
			log.info("유효한 토큰이 없습니다. ({})", ((HttpServletRequest) request).getRequestURI());

		chain.doFilter(request, response);
	}

	private String resolveJwtTokenByRequestHeader(ServletRequest request) {
		return SecurityUtils.substringBearerString(((HttpServletRequest)request).getHeader(HttpHeaders.AUTHORIZATION));
	}

}
