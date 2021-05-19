package project.capstone.fick.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.capstone.fick.service.structure.StructureService;
import project.capstone.fick.web.dto.structure.StructureResponseDto;
import project.capstone.fick.web.dto.structure.StructureSaveRequestDto;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api/v1/structure")
@RestController
public class StructureApiController {

	private final StructureService structureService;

	@GetMapping("/{id}")
	public StructureResponseDto structureResponseDto(@PathVariable Long id) {
		return structureService.structureResponseDto(id);
	}

	@GetMapping("/list")
	public List<StructureResponseDto.IdAndName> structureList() {
		return structureService.findAll();
	}

	@PostMapping
	public Long saveStructure(@RequestBody StructureSaveRequestDto dto) {
		return structureService.saveStructure(dto);
	}

	@DeleteMapping("/{id}")
	public Long deleteStructure(@PathVariable Long id) {
		structureService.deleteStructureById(id);
		return id;
	}
}
