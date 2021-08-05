package project.capstone.fick.web.dto.user;

import lombok.Getter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@Getter
public class AuthenticateUserRequestDto {

	@Length(max = 20)
	@NotBlank
	private String username;

	@Length(max = 20)
	@NotBlank
	private String password;
}
