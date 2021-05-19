package project.capstone.fick.web.dto.structure;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import project.capstone.fick.domain.Location;
import project.capstone.fick.domain.structure.Structure;
import project.capstone.fick.web.dto.crack.CrackListResponseDto;

import java.time.LocalDateTime;
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

	private LocalDateTime createdDate;

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
		this.createdDate = structure.getCreatedDate();
		this.cracks = cracks;
	}

	@Getter
	public static class IdAndName {
		private Long id;
		private String name;

		public IdAndName(Structure structure) {
			this.id = structure.getId();
			this.name = structure.getName();
		}
	}
}
