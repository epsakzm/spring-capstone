package project.capstone.fick.web;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import project.capstone.fick.service.CrackService;
import project.capstone.fick.web.dto.crack.CrackResponseDto;
import project.capstone.fick.web.dto.crack.CrackSaveRequestDto;
import project.capstone.fick.web.dto.crack.CrackUpdateRequestDto;

@RequiredArgsConstructor
@RequestMapping("/api/v1/crack")
@RestController
public class CrackApiController {

	private final CrackService crackService;

	@PostMapping
	public Long saveCrack(@RequestBody CrackSaveRequestDto crackSaveRequestDto) {
		return crackService.saveCrack(crackSaveRequestDto);
	}

	@GetMapping("/{id}")
	public CrackResponseDto responseCrackDto(@PathVariable Long id) {
		return crackService.findById(id);
	}

	@PutMapping("/{id}")
	public Long updateCrack(@PathVariable Long id, @RequestBody CrackUpdateRequestDto dto) {
		return crackService.updateCrack(id, dto);
	}

	@DeleteMapping("/{id}")
	public Long delete(@PathVariable Long id) {
		crackService.deleteCrackById(id);
		return id;
	}

}
