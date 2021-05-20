package project.capstone.fick.service.xlsx;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.*;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import project.capstone.fick.domain.crack.Crack;
import project.capstone.fick.domain.project.Project;
import project.capstone.fick.domain.structure.Structure;
import project.capstone.fick.service.ServiceUtil;
import project.capstone.fick.web.dto.crack.CrackExcelResponseDto;
import project.capstone.fick.web.dto.project.ProjectExcelResponseDto;
import project.capstone.fick.web.dto.structure.StructureExcelResponseDto;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.FileOutputStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class XlsxService {

	private final ServiceUtil serviceUtil;

	private static final int FONT_SIZE = 14;

	private static final int BOLD_FONT_SIZE = 20;

	private static final int MAX_COLUMN = 15;

	public ResponseEntity<?> xssfDownload(HttpServletResponse response,
										  Long userId) {
		ServletOutputStream outputStream;
		XSSFWorkbook workbook;
		XSSFSheet sheet;
		int sheetNum = 0;
		int rowNum;

		try {
			workbook = new XSSFWorkbook();
			List<Project> projects = serviceUtil.findProjectByUserId(userId);
			for (Project project : projects) {
				sheet = createSheet(workbook, String.format("%d - %s", ++sheetNum, project.getName()));
				rowNum = definitionProject(project, workbook, sheet);

				List<Structure> structures = serviceUtil.findStructureByProjectId(project.getId());
				for (Structure structure : structures) {
					rowNum = definitionStructure(structure, workbook, sheet, rowNum);
					List<Crack> cracks = serviceUtil.findCrackByStructureId(structure.getId());
					if (!cracks.isEmpty())
						rowNum = definitionCracks(cracks, workbook, sheet, rowNum);
					else
						rowNum = noCracks(workbook, sheet, rowNum);
				}
				for(int i = 0; i < MAX_COLUMN; ++i) {
					sheet.autoSizeColumn(i);
				}
			}
			//Servlet Response
			outputStream = response.getOutputStream();
			workbook.write(outputStream);

			outputStream.flush();
			outputStream.close();
//			FileOutputStream fos = new FileOutputStream("/Users/hwpark/workspace/test.xls");
//			workbook.write(fos);
//			fos.close();
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok().build();
	}

	private int noCracks(XSSFWorkbook workbook, XSSFSheet sheet, int rowNum) {
		XSSFRow row = sheet.createRow(rowNum - 1);
		row.createCell(0).setCellValue("NO CRACKS");
		setBoldStyleRow(workbook, row, 1);
		return rowNum + 2;
	}

	private int definitionCracks(List<Crack> cracks, XSSFWorkbook workbook, XSSFSheet sheet, int rowNum) {
		Field[] crackExcelClassFields
			= CrackExcelResponseDto.class.getFields();
		final int HEADER_SIZE
			= crackExcelClassFields.length;
		XSSFRow row;

		//set header
		setBoldStyle(
			workbook,
			createCell(
				createRow(sheet, rowNum), "균열 리스트", 0),
			BOLD_FONT_SIZE);
		sheet.addMergedRegion(
			new CellRangeAddress(rowNum, rowNum++, 0, HEADER_SIZE - 1));

		row	= createRow(sheet, rowNum++);
		for (int i = 0; i < HEADER_SIZE; ++i) {
			setBoldStyle(
				workbook,
				createCell(
					row,
					crackExcelClassFields[i].getName(),
					i),
				FONT_SIZE);
		}

		for (Crack crack : cracks) {
			new CrackExcelResponseDto(crack)
				.createCrackCells(
					createRow(sheet, rowNum++));
		}
		return rowNum + 1;
	}

	private int definitionStructure(Structure structure, XSSFWorkbook workbook, XSSFSheet sheet, int rowNum) {
		final int HEADER_SIZE
			= StructureExcelResponseDto.class.getFields().length;
		StructureExcelResponseDto structureExcelResponseDto
			= new StructureExcelResponseDto(structure);
		XSSFRow row;

		setBoldStyle(
			workbook,
			createCell(
				createRow(sheet, rowNum),
				"구조물 - " + structureExcelResponseDto.getName(),
				0),
			BOLD_FONT_SIZE);
		sheet.addMergedRegion(
			new CellRangeAddress(rowNum, rowNum++, 0, HEADER_SIZE - 1));

		row = createRow(sheet, rowNum++);
		structureExcelResponseDto.createStructureHeaderCells(row);
		setBoldStyleRow(workbook, row, HEADER_SIZE);

		row = createRow(sheet, rowNum++);
		structureExcelResponseDto.createStructureCells(row);

		return rowNum + 1;
	}

	private void setBoldStyle(XSSFWorkbook workbook, XSSFCell cell, int fontSize) {
		XSSFFont font = workbook.createFont();

		font.setBold(true);
		font.setFontHeightInPoints((short)fontSize);
		cell.setCellStyle(addStyle(workbook, font));
	}

	private XSSFCellStyle addStyle(XSSFWorkbook workbook, XSSFFont font) {
		XSSFCellStyle style = workbook.createCellStyle();
		style.setFont(font);

		return style;
	}

	private int definitionProject(Project project, XSSFWorkbook workbook, XSSFSheet sheet) {
		final int HEADER_SIZE
			= ProjectExcelResponseDto.class.getFields().length;
		XSSFRow row;

		ProjectExcelResponseDto projectExcelResponseDto
			= new ProjectExcelResponseDto(project);

		setBoldStyle(
			workbook,
			createCell(
				createRow(sheet, 0),
				projectExcelResponseDto.getName(),
				0),
			BOLD_FONT_SIZE);
		sheet.addMergedRegion(
			new CellRangeAddress(0, 0, 0, HEADER_SIZE - 1));

		row = createRow(sheet, 1);
		projectExcelResponseDto
			.createProjectHeaderCells(row);
		setBoldStyleRow(workbook, row, HEADER_SIZE);

		row = createRow(sheet, 2);
		projectExcelResponseDto
			.createProjectCells(row);

		return 4;
	}

	private void setBoldStyleRow(XSSFWorkbook workbook, XSSFRow row, int size) {
		for (int i = 0; i < size; ++i) {
			setBoldStyle(workbook, row.getCell(i), FONT_SIZE);
		}
	}

	private String createFileName(Long id) {
		String fileName = id + "_"  + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));

		String encName;
		try{
			encName = new String(fileName.getBytes(StandardCharsets.UTF_8), "8859_1");
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException("인코딩 오류");
		}
		return encName;
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

	public String getHeaderFileName(Long userId) {
		return createFileName(userId);
	}
}
