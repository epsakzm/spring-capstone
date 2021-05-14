package project.capstone.fick.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.capstone.fick.service.xlsx.XlsxService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/excel")
public class DownloadApiController {

	private final XlsxService xlsxService;

	@GetMapping("/{id}")
	public ResponseEntity<?> download(HttpServletRequest request,
									  HttpServletResponse response,
									  @PathVariable("id") Long userId) {
		return xlsxService.xssfDownload(request, response, userId);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> exceptionHandler(Exception e) {
		return ResponseEntity.badRequest().body(e.getMessage());
	}
}
