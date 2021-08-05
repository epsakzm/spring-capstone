package project.capstone.fick.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import project.capstone.fick.domain.crack.Crack;
import project.capstone.fick.domain.project.Project;
import project.capstone.fick.domain.structure.Structure;
import project.capstone.fick.service.crack.CrackService;
import project.capstone.fick.service.project.ProjectService;
import project.capstone.fick.service.structure.StructureService;

import java.util.*;

@RequiredArgsConstructor
@Component
public class ServiceUtils implements ApplicationContextAware {

	private final CrackService crackService;

	private final StructureService structureService;

	private final ProjectService projectService;

	private static ApplicationContext applicationContext;
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
		return crackService.findCrackByStructureId(structureId);
	}

	public List<Structure> findStructureByProjectId(Long projectId) {
		return structureService.findStructureByProjectId(projectId);
	}

	public List<Project> findProjectByUserId(Long userId) {
		return projectService.findProjectByUserId(userId);
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		ServiceUtils.applicationContext = applicationContext;
	}

	public static <T> T getBean(Class<T> clazz) {
		return applicationContext.getBean(clazz);
	}

}
