package project.capstone.fick.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Slf4j
public class SecurityUtils {

	public static final String BEARER = "Bearer ";

	private SecurityUtils() {}

	public static Optional<String> getCurrentUsername() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		if (authentication == null) {
			log.info("Security Context 에 인증 정보가 없음.");
			return Optional.empty();
		}

		Object principal = authentication.getPrincipal();

		if (principal == null)
			log.info("사용자 정보 없음");
		else {
			log.info("현재 사용자 : " + principal);
			if (principal instanceof UserDetails)
				return Optional.ofNullable(((UserDetails) principal).getUsername());
			else if (principal instanceof String)
				return Optional.of((String)principal);
		}
		return Optional.empty();
	}

	public static String substringBearerString(String stringWithBearer) {
		return StringUtils.hasText(stringWithBearer) && stringWithBearer.startsWith(BEARER) ?
			stringWithBearer.substring(7) : null;
	}

	public static String resolveRequestURI(ServletRequest request) {
		return ((HttpServletRequest)request).getRequestURI();
	}

}
