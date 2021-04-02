package project.capstone.fick.web;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import project.capstone.fick.service.crack.CrackService;
import project.capstone.fick.web.dto.crack.CrackResponseDto;
import project.capstone.fick.web.dto.crack.CrackSaveRequestDto;
import project.capstone.fick.web.dto.crack.CrackUpdateIsCrackRequestDto;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class CrackApiController {

	private final CrackService crackService;

	@PostMapping("/api/v1/crack")
	public Long saveCrack(@RequestBody CrackSaveRequestDto crackSaveRequestDto) {
		return crackService.saveCrack(crackSaveRequestDto);
	}

	@GetMapping("/api/v1/crack/{id}")
	public CrackResponseDto responseCrack(@PathVariable Long id) {
		return crackService.findById(id);
	}

	@PutMapping("/api/v1/crack/{id}")
	public Long updateIsCrack(@PathVariable Long id,
							  @RequestBody CrackUpdateIsCrackRequestDto dto) {
		return crackService.updateIsCrack(id, dto);
	}

	@DeleteMapping("/api/v1/crack/{id}")
	public Long delete(@PathVariable Long id) {
		crackService.delete(id);
		return id;
	}
}
