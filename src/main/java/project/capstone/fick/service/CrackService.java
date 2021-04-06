package project.capstone.fick.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.capstone.fick.domain.crack.Crack;
import project.capstone.fick.domain.crack.CrackRepository;
import project.capstone.fick.domain.crack.CrackRiskLevel;
import project.capstone.fick.domain.structure.Structure;
import project.capstone.fick.domain.structure.StructureRepository;
import project.capstone.fick.web.dto.crack.CrackResponseDto;
import project.capstone.fick.web.dto.crack.CrackSaveRequestDto;
import project.capstone.fick.web.dto.crack.CrackUpdateIsCrackRequestDto;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CrackService {

	private final CrackRepository crackRepository;

	private final StructureRepository structureRepository;

	@Transactional
	public Long saveCrack(CrackSaveRequestDto dto) {
		CrackRiskLevel level = CrackRiskLevel.UNKNOWN;
		int dtoRiskLevel = Optional.ofNullable(dto.getRiskLevelInteger()).orElse(0);
		if (dtoRiskLevel == 1) {
			level = CrackRiskLevel.LOW;
		} else if (dtoRiskLevel == 2) {
			level = CrackRiskLevel.MEDIUM;
		} else if (dtoRiskLevel == 3) {
			level = CrackRiskLevel.HIGH;
		}
		Structure structure = structureRepository.findById(dto.getStructureId()).orElseThrow(IllegalArgumentException::new);
		Crack savedCrack = dto.toCrackEntity(structure, level);
		structure.addCrack(savedCrack);
		return crackRepository.save(savedCrack).getId();
	}

	@Transactional(readOnly = true)
	public CrackResponseDto findById(Long id) {
		Crack crack = crackRepository.findById(id)
			.orElseThrow(IllegalArgumentException::new);
		return new CrackResponseDto(crack);
	}

	@Transactional
	public Long updateIsCrack(Long id, CrackUpdateIsCrackRequestDto dto) {
		Crack crack = crackRepository.findById(id)
			.orElseThrow(IllegalArgumentException::new);
		crack.updateIsCrack(dto.getIsCrack());
		return id;
	}

	@Transactional
	public void delete(Long id) {
		Crack crack = crackRepository.findById(id)
			.orElseThrow(IllegalArgumentException::new);
		crackRepository.delete(crack);
	}
}
