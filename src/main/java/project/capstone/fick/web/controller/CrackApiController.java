package project.capstone.fick.web.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import project.capstone.fick.service.crack.CrackService;
import project.capstone.fick.web.dto.crack.CrackResponseDto;
import project.capstone.fick.web.dto.crack.CrackSaveRequestDto;

@Slf4j
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

	@DeleteMapping("/{id}")
	public Long delete(@PathVariable Long id) {
		crackService.deleteCrackById(id);
		return id;
	}

}
