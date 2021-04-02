package project.capstone.fick.web.dto.crack;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

// 어떻게오더라
@Getter
@NoArgsConstructor
public class CrackUpdateIsCrackRequestDto {

	private Boolean isCrack;

	@Builder
	public CrackUpdateIsCrackRequestDto(Boolean isCrack) {
		this.isCrack = isCrack;
	}
}
