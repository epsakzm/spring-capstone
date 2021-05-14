package project.capstone.fick.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import project.capstone.fick.domain.crack.Crack;
import project.capstone.fick.domain.project.Project;
import project.capstone.fick.domain.structure.Structure;
import project.capstone.fick.service.crack.CrackService;
import project.capstone.fick.service.project.ProjectService;
import project.capstone.fick.service.structure.StructureService;
import project.capstone.fick.service.user.UserService;

import java.util.*;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class ServiceUtil {

	private final CrackService crackService;

	private final StructureService structureService;

	private final ProjectService projectService;
//
//	public Long countCrackByStructureId(Long structureId) {
//		return crackService.countCrackByStructureId(structureId);
//	}
//
//	public Long countStructureByProjectId(Long projectId) {
//		return structureService.countStructureByProjectId(projectId);
//	}
//
//	public Long countProjectByUserId(Long userId) {
//		return projectService.countProjectByUserId(userId);
//	}

	public List<Crack> findCrackByStructureId(Long structureId) {
		return crackService.findCrackListByStructureId(structureId);
	}

	public List<Structure> findStructureByProjectId(Long projectId) {
		return structureService.findStructureByProjectId(projectId);
	}

	public List<Project> findProjectByUserId(Long userId) {
		return projectService.findProjectByUserId(userId);
	}
}
