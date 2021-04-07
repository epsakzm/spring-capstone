package project.capstone.fick.web;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import project.capstone.fick.service.StructureService;
import project.capstone.fick.web.dto.structure.StructureResponseDto;
import project.capstone.fick.web.dto.structure.StructureSaveRequestDto;

@RequiredArgsConstructor
@RestController
public class StructureApiController {

	private final StructureService structureService;

	@GetMapping("/api/v1/structure/{id}")
	public StructureResponseDto structureResponseDto(@PathVariable Long id) {
		return null;
	}

	@PostMapping("/api/v1/structure")
	public Long saveStructure(@RequestBody StructureSaveRequestDto dto) {
		return structureService.saveStructure(dto);
	}
}
