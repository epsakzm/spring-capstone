package project.capstone.fick.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.capstone.fick.domain.project.Project;
import project.capstone.fick.domain.project.ProjectRepository;
import project.capstone.fick.domain.structure.StructureRepository;
import project.capstone.fick.web.dto.structure.StructureSaveRequestDto;

@RequiredArgsConstructor
@Service
public class StructureService {

	private final StructureRepository structureRepository;

	private final ProjectRepository projectRepository;

	@Transactional
	public Long saveStructure(StructureSaveRequestDto dto) {
		Project project = projectRepository.findById(dto.getProjectId())
			.orElseThrow(IllegalArgumentException::new);
		return structureRepository.save(dto.toStructureEntity(project)).getId();
	}
}
