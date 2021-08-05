package project.capstone.fick.security.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Role {

	GUEST("ROLE_GUEST", "Guest"),
	USER("ROLE_USER", "User"),
	ADMIN("ROLE_ADMIN", "Admin");

	private final String key;
	private final String value;
}
