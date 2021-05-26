package project.capstone.fick.web.dto.crack;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import project.capstone.fick.domain.crack.Crack;
import project.capstone.fick.domain.crack.CrackRiskLevel;

@Getter
@NoArgsConstructor
public class CrackUpdateRequestDto {

	private Boolean isCrack;

	private String comment;

	private CrackRiskLevel riskLevel;

	@Builder
	public CrackUpdateRequestDto(Boolean isCrack, String comment, CrackRiskLevel riskLevel) {
		this.isCrack = isCrack;
		this.comment = comment;
		this.riskLevel = riskLevel;
	}
}
