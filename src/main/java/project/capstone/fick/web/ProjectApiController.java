package project.capstone.fick.web;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import project.capstone.fick.service.ProjectService;
import project.capstone.fick.web.dto.project.ProjectSaveRequestDto;
import project.capstone.fick.web.dto.project.ProjectResponseDto;

@RequiredArgsConstructor
@RequestMapping("/api/v1/project")
@RestController
public class ProjectApiController {

	private final ProjectService projectService;

	@GetMapping("/{id}")
	public ProjectResponseDto findById(@PathVariable Long id) {
		return projectService.findById(id);
	}

	@PostMapping
	public Long saveProject(@RequestBody ProjectSaveRequestDto dto) {
		return projectService.saveProject(dto);
	}

	@DeleteMapping("/{id}")
	public Long deleteProject(@PathVariable Long id) {
		projectService.deleteProjectById(id);
		return id;
	}
}
