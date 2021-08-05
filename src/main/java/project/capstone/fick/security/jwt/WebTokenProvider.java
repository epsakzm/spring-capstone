package project.capstone.fick.security.jwt;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import project.capstone.fick.security.SecurityUtils;

import java.security.Key;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Component
public class WebTokenProvider implements InitializingBean {

	private static final String JWT_ISSUER = 					"Application Fick";
	private static final String AUTH_KEY = 						"auth";
	private static final SignatureAlgorithm ALGORITHM_HS512 = 	SignatureAlgorithm.HS512;

	private final String base64Secret;

	private Key secretKey;


	public WebTokenProvider(@Value("${jwt.secret}") String base64Secret) {
		this.base64Secret = base64Secret;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		byte[] decodedSecret = Decoders.BASE64.decode(base64Secret);
		secretKey = Keys.hmacShaKeyFor(decodedSecret);
	}

	public String createTokenValue(Authentication authentication) {
		String authorities = authentication.getAuthorities().stream()
			.map(GrantedAuthority::getAuthority)
			.collect(Collectors.joining(","));

		long currentMilliseconds = new Date().getTime();
		long tokenValidityMilliseconds = SecurityUtils.TOKEN_VALIDITY_SECONDS * 1000L;
		Date validityDate = new Date(currentMilliseconds + tokenValidityMilliseconds);

		return Jwts.builder()
			.setSubject(authentication.getName())
			.setIssuer(JWT_ISSUER)
			.claim(AUTH_KEY, authorities)
			.setExpiration(validityDate)
			.setIssuedAt(new Date())
			.signWith(secretKey, ALGORITHM_HS512)
			.compact();
	}

	public Authentication getAuthenticationByTokenValue(String tokenValue) {
		// jwt 파싱
		JwtParser jwtParser = Jwts.parserBuilder()
			.setSigningKey(secretKey)
			.build();
		Claims claims = jwtParser.parseClaimsJws(tokenValue).getBody();

		// authority 정보
		List<SimpleGrantedAuthority> authorities = Arrays.stream(claims.get(AUTH_KEY).toString().split(","))
			.map(SimpleGrantedAuthority::new)
			.collect(Collectors.toList());

		org.springframework.security.core.userdetails.User userDetails = new User(claims.getSubject(), null, authorities);

		return new UsernamePasswordAuthenticationToken(userDetails, tokenValue, authorities);
	}

	public boolean validateToken(String tokenValue) {
		try {
			JwtParser jwtParser = Jwts.parserBuilder().setSigningKey(secretKey).build();
			jwtParser.parseClaimsJws(tokenValue);
			return true;
		} catch (io.jsonwebtoken.security.SecurityException | MalformedJwtException e) {
			log.info("잘못된 JWT 서명입니다.");
		} catch (ExpiredJwtException e) {
			log.info("만료된 JWT 토큰입니다.");
		} catch (UnsupportedJwtException e) {
			log.info("지원되지 않는 JWT 토큰입니다.");
		} catch (IllegalArgumentException e) {
			log.info("JWT 토큰이 잘못되었습니다.");
		}
		return false;
	}

}
