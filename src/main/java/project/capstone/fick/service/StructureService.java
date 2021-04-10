package project.capstone.fick.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.capstone.fick.domain.project.Project;
import project.capstone.fick.domain.project.ProjectRepository;
import project.capstone.fick.domain.structure.Structure;
import project.capstone.fick.domain.structure.StructureRepository;
import project.capstone.fick.web.dto.crack.CrackListResponseDto;
import project.capstone.fick.web.dto.structure.StructureResponseDto;
import project.capstone.fick.web.dto.structure.StructureSaveRequestDto;
import project.capstone.fick.web.dto.structure.StructureUpdateRequestDto;

import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class StructureService {

	private final StructureRepository structureRepository;

	private final ProjectRepository projectRepository;

	@Transactional(readOnly = true)
	public StructureResponseDto structureResponseDto(Long id) {
		Structure structure = structureRepository.findStructureByJoinFetch(id).orElseThrow(IllegalArgumentException::new);
		return StructureResponseDto.builder()
			.structure(structure)
			.cracks(structure.getCrackList().stream().map(CrackListResponseDto::new).collect(Collectors.toList()))
			.build();
	}

	@Transactional
	public Long saveStructure(StructureSaveRequestDto dto) {
		Project project = projectRepository.findById(dto.getProjectId())
			.orElseThrow(IllegalArgumentException::new);
		Structure savedStructure = structureRepository.save(dto.toStructureEntity(project));
		project.addStructure(savedStructure);
		return savedStructure.getId();
	}

	@Transactional
	public Long updateStructure(StructureUpdateRequestDto dto) {
		return 1L;
	}

	@Transactional
	public void deleteStructureById(Long id) {
		Structure structure = structureRepository.findById(id).orElseThrow(IllegalArgumentException::new);
		structureRepository.delete(structure);
	}

}
