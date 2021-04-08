package project.capstone.fick.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.capstone.fick.domain.project.ProjectRepository;
import project.capstone.fick.web.dto.project.ProjectResponseDto;
import project.capstone.fick.web.dto.project.ProjectSaveRequestDto;
import project.capstone.fick.web.dto.project.ProjectUpdateRequestDto;

@RequiredArgsConstructor
@Service
public class ProjectService {

	private final ProjectRepository projectRepository;

	@Transactional(readOnly = true)
	public ProjectResponseDto findById(Long id) {
		return null;
	}

	public Long saveProject(ProjectSaveRequestDto dto) {
		return 1L;
	}

	public Long updateProject(Long id, ProjectUpdateRequestDto dto) {
		return 1L;
	}

	public void deleteProjectById(Long id) {
	}
}
