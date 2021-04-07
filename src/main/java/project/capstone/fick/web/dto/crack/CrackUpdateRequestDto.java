package project.capstone.fick.web.dto.crack;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CrackUpdateRequestDto {

	private Boolean isCrack;

	@Builder
	public CrackUpdateRequestDto(Boolean isCrack) {
		this.isCrack = isCrack;
	}
}
