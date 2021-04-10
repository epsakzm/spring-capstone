package project.capstone.fick.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.capstone.fick.domain.Location;
import project.capstone.fick.domain.project.Project;
import project.capstone.fick.domain.project.ProjectRepository;
import project.capstone.fick.domain.user.User;
import project.capstone.fick.domain.user.UserRepository;
import project.capstone.fick.web.dto.project.ProjectResponseDto;
import project.capstone.fick.web.dto.project.ProjectSaveRequestDto;
import project.capstone.fick.web.dto.project.ProjectUpdateRequestDto;
import project.capstone.fick.web.dto.structure.StructureListResponseDto;

import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ProjectService {

	private final ProjectRepository projectRepository;

	private final UserRepository userRepository;

	@Transactional(readOnly = true)
	public ProjectResponseDto findById(Long id) {
		Project project = projectRepository.findProjectByJoinFetch(id).orElseThrow(IllegalArgumentException::new);
		return ProjectResponseDto.builder()
			.project(project)
			.structures(project.getStructureList().stream().map(StructureListResponseDto::new).collect(Collectors.toList()))
			.build();
	}

	@Transactional
	public Long saveProject(ProjectSaveRequestDto dto) {
		User user = userRepository.findById(dto.getUserId()).orElseThrow(IllegalArgumentException::new);
		Project savedProject = projectRepository.save(Project.builder()
			.name(dto.getName())
			.location(Location.builder()
				.locationX(dto.getLocationX())
				.locationY(dto.getLocationY())
				.locationDetail(dto.getLocationDetail())
				.build())
			.photoUrl(dto.getPhotoUrl())
			.comment(dto.getComment())
			.user(user)
			.build());
		user.addProject(savedProject);
		return savedProject.getId();
	}

	@Transactional
	public Long updateProject(Long id, ProjectUpdateRequestDto dto) {
		return 1L;
	}

	@Transactional
	public void deleteProjectById(Long id) {
		Project project = projectRepository.findById(id).orElseThrow(IllegalArgumentException::new);
		projectRepository.delete(project);
	}
}
