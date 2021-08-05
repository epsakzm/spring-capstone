package project.capstone.fick.service.security;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import project.capstone.fick.domain.user.User;
import project.capstone.fick.security.UserNotActivatedException;
import project.capstone.fick.service.user.UserService;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

// TODO
@Slf4j
@RequiredArgsConstructor
@Service
public class CustomUserDetailsService implements UserDetailsService {

	private final UserService userService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userService.findUserByUsername(username)
			.orElseThrow(() -> new UsernameNotFoundException(username + "에 해당하는 사용자가 없습니다."));
		user.setActivated();

		return createUserDetails(user);
	}

	private UserDetails createUserDetails(User user) {
		if (!user.isActivated())
			throw new UserNotActivatedException(user.getName() + "사용자는 사용중이 아닙니다.");
		List<? extends GrantedAuthority> authorities = Stream.of(user.getRole().getKey())
				.map(SimpleGrantedAuthority::new)
				.collect(Collectors.toList());
		log.info("ROLES : {}", user.getRole().getKey());
		return new org.springframework.security.core.userdetails.User(user.getUID().toString(), user.getPassword(), authorities);
	}
}
