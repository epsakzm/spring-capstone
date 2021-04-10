package project.capstone.fick.web.dto.structure;

import lombok.Builder;
import lombok.Getter;
import project.capstone.fick.domain.Location;
import project.capstone.fick.domain.structure.Structure;
import project.capstone.fick.web.dto.crack.CrackListResponseDto;
import project.capstone.fick.web.dto.crack.CrackResponseDto;
import project.capstone.fick.web.dto.project.ProjectResponseDto;

import java.util.List;

@Getter
public class StructureResponseDto {

	private Long id;

	private String name;

	private String comment;

	private Double height;

	private Boolean isWorkDone;

	private String modelUrl;

	private Location location;

	private String projectName;

	private List<CrackListResponseDto> cracks;

	@Builder
	public StructureResponseDto (Structure structure,
								 List<CrackListResponseDto> cracks) {
		this.id = structure.getId();
		this.name = structure.getName();
		this.height = structure.getHeight();
		this.comment = structure.getComment();
		this.isWorkDone = structure.getIsWorkDone();
		this.modelUrl = structure.getModelUrl();
		this.location = structure.getLocation();
		this.projectName = structure.getProject().getName();
		this.cracks = cracks;
	}
}
