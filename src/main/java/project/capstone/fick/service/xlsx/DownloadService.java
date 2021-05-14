package project.capstone.fick.service.xlsx;

import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.*;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import project.capstone.fick.domain.crack.Crack;
import project.capstone.fick.domain.project.Project;
import project.capstone.fick.domain.structure.Structure;
import project.capstone.fick.service.ServiceUtil;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RequiredArgsConstructor
@Service
public class DownloadService {

	private final ServiceUtil serviceUtil;

	private static final int FIELD_PROJECT = 6;

	public ResponseEntity<?> xssfDownload(HttpServletRequest request,
										  HttpServletResponse response,
										  Long userId) {
		XSSFWorkbook workbook = null;
		XSSFSheet sheet = null;
		XSSFRow row = null;
		int sheetNum = 0;
		int rowNum;

		try {
			workbook = new XSSFWorkbook();
			List<Project> projects = serviceUtil.findProjectByUserId(userId);
			for (Project project : projects) {
				sheet = createSheet(workbook, String.format("%d - %s", sheetNum, project.getName()));
				definitionProject(project, workbook, sheet);
				//rowNum = 3, used 0, 1, 2
				for (Structure structure : serviceUtil.findStructureByProjectId(project.getId())) {

					for (Crack crack : serviceUtil.findCrackByStructureId(structure.getId())) {

					}
				}
			}
			FileOutputStream fos = new FileOutputStream("/Users/hwpark/workspace/" + createFileName(userId) + "xlsx");
			workbook.write(fos);

			fos.close();
			System.out.println("done");
		} catch (Exception e) {
			System.out.println("message");
			System.out.println(e.getMessage());
		}
//		ByteArrayOutputStream baos = new ByteArrayOutputStream();
//		workbook.write(baos);
//		baos.flush();
//		baos.close();
//
//		ServletOutputStream servletOutputStream = response.getOutputStream();
//		servletOutputStream.write(baos.toByteArray());
//		servletOutputStream.flush();
//		servletOutputStream.close();
		return ResponseEntity.ok().build();
	}

	private XSSFFont setFont(XSSFWorkbook workbook, boolean bold, int fontSize) {
		XSSFFont font = workbook.createFont();

		font.setBold(bold);
		font.setFontHeightInPoints((short)fontSize);
		return font;
	}

	private XSSFCellStyle setCellStyle(XSSFWorkbook workbook, XSSFFont font) {
		XSSFCellStyle style = workbook.createCellStyle();
		style.setFont(font);

		return style;
	}

	private void definitionProject(Project project, XSSFWorkbook workbook, XSSFSheet sheet) {
		XSSFRow row;

		row = createRow(sheet, 0);


		row = createRow(sheet, 1);

		//set header
		createCell(row, "id", 0);
		createCell(row, "x", 1);
		createCell(row, "y", 2);
		createCell(row, "location", 3);
		createCell(row, "comment", 4);
		for (int i = 0; i < 5; ++i) {
			row.getCell(i).setCellStyle(setCellStyle(workbook, setFont(workbook, true, 14)));
		}

		//set cell contents
		row = createRow(sheet, 2);
		createCell(row, project.getId().toString(), 0);
		createCell(row, project.getLocation().getLocationX().toString(), 1);
		createCell(row, project.getLocation().getLocationY().toString(), 2);
		createCell(row, project.getLocation().getLocationDetail(), 3);
		createCell(row, project.getComment(), 4);
	}

	private String createFileName(Long id) {
		return id +
			"_" +
			LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
	}

	private XSSFCell createCell(XSSFRow row, String content, int cellNum) {
		XSSFCell cell = row.createCell(cellNum);
		cell.setCellValue(content);
		return cell;
	}

	private XSSFRow createRow(XSSFSheet sheet, int rowNum) {
		return sheet.createRow(rowNum);
	}

	private XSSFSheet createSheet(XSSFWorkbook workbook, String sheetName) {
		return workbook.createSheet(sheetName);
	}
}
