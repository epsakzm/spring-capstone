package project.capstone.fick.web;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import project.capstone.fick.service.StructureService;
import project.capstone.fick.web.dto.structure.StructureResponseDto;
import project.capstone.fick.web.dto.structure.StructureSaveRequestDto;
import project.capstone.fick.web.dto.structure.StructureUpdateRequestDto;

@RequiredArgsConstructor
@RequestMapping("/api/v1/structure")
@RestController
public class StructureApiController {

	private final StructureService structureService;

	@GetMapping("/{id}")
	public StructureResponseDto structureResponseDto(@PathVariable Long id) {
		return structureService.structureResponseDto(id);
	}

	@PostMapping
	public Long saveStructure(@RequestBody StructureSaveRequestDto dto) {
		return structureService.saveStructure(dto);
	}

	@PutMapping("/{id}")
	public Long updateStructure(@RequestBody StructureUpdateRequestDto dto) {
		return structureService.updateStructure(dto);
	}

	@DeleteMapping("/{id}")
	public Long deleteStructure(@PathVariable Long id) {
		structureService.deleteStructureById(id);
		return id;
	}
}
