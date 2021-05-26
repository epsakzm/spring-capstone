package project.capstone.fick.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.capstone.fick.service.xlsx.XlsxService;

import javax.servlet.http.HttpServletResponse;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/excel")
public class DownloadApiController {

	private final XlsxService xlsxService;

	@GetMapping("/{id}")
	public ResponseEntity<?> downloadExcel(HttpServletResponse response,
										   @PathVariable("id") Long projectId) {
		String fileName = xlsxService.getHeaderFileName(projectId);
		response.setHeader("Content-Disposition", "attachment; fileName=\"" + fileName + ".xls\"");
		response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
		return xlsxService.xssfDownloadByProject(response, projectId);
	}

	@GetMapping("/user/{id}")
	public ResponseEntity<?> downloadExcelByUser(HttpServletResponse response,
										   @PathVariable("id") Long userId) {
		String fileName = xlsxService.getHeaderFileName(userId);
		response.setHeader("Content-Disposition", "attachment; fileName=\"" + fileName + ".xls\"");
		response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
		return xlsxService.xssfDownloadByUser(response, userId);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> exceptionHandler(Exception e) {
		return ResponseEntity.badRequest().body(e.getMessage());
	}
}
