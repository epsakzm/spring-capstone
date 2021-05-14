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
import java.io.FileOutputStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

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
		XSSFWorkbook workbook = null;
		XSSFSheet sheet = null;
		XSSFRow row = null;
		int sheetNum = 0;
		int rowNum;
//		String s = new String(fileNameOrg.getBytes("UTF-8"), "ISO-8859-1");
		try {
			workbook = new XSSFWorkbook();
			List<Project> projects = serviceUtil.findProjectByUserId(userId);
			for (Project project : projects) {
				sheet = createSheet(workbook, String.format("%d - %s", ++sheetNum, project.getName()));
				rowNum = definitionProject(project, workbook, sheet);
				//rowNum = 3, used 0, 1, 2
				List<Structure> structures = serviceUtil.findStructureByProjectId(project.getId());
				for (Structure structure : structures) {
					rowNum = definitionStructure(structure, workbook, sheet, rowNum);
					List<Crack> cracks = serviceUtil.findCrackByStructureId(structure.getId());
					if (!cracks.isEmpty())
						rowNum = definitionCracks(cracks, workbook, sheet, rowNum);
					//TODO 균열이 없음을 표시하는 구역
					else
						continue;
				}
				for(int i = 0; i < MAX_COLUMN; ++i) {
					sheet.autoSizeColumn(i);
				}
			}
			//file Output
//			FileOutputStream fos = new FileOutputStream("/Users/hwpark/workspace/"+ createFileName(userId) +".xlsx");
//			workbook.write(fos);
//			fos.close();

			//Servlet Response
			outputStream = response.getOutputStream();
			workbook.write(outputStream);

			outputStream.flush();
			outputStream.close();
		} catch (Exception e) {
			System.out.println("message");
			e.printStackTrace();
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

	private int definitionCracks(List<Crack> cracks, XSSFWorkbook workbook, XSSFSheet sheet, int rowNum) {
		final int HEADER_SIZE = 8;
		XSSFRow row;
		XSSFCell cell;

		//set header
		row = createRow(sheet, rowNum);
		cell = createCell(row, "균열", 0);
		setBoldStyle(workbook, cell, BOLD_FONT_SIZE);
		sheet.addMergedRegion(new CellRangeAddress(rowNum, rowNum, 0, HEADER_SIZE - 1));
		++rowNum;

		row = createRow(sheet, rowNum++);
		createCell(row, "id", 0);
		createCell(row, "width", 1);
		createCell(row, "height", 2);
		createCell(row, "x", 3);
		createCell(row, "y", 4);
		createCell(row, "risk level", 5);
		createCell(row, "location", 6);
		createCell(row, "comment", 7);
		for(int i = 0; i < HEADER_SIZE; ++i) {
			setBoldStyle(workbook, row.getCell(i), FONT_SIZE);
		}

		// set cell contents
		for(int i = 0; i < cracks.size(); ++i) {
			Crack crack = cracks.get(i);
			row = createRow(sheet, rowNum++);
			createCell(row, crack.getId().toString(), 0);
			createCell(row, crack.getWidth().toString(), 1);
			createCell(row, crack.getHeight().toString(), 2);
			createCell(row, crack.getLocation().getLocationX().toString(), 3);
			createCell(row, crack.getLocation().getLocationY().toString(), 4);
			createCell(row, crack.getRiskLevel().toString(), 5);
			createCell(row, crack.getLocation().getLocationDetail(), 6);
			createCell(row, crack.getComment(), 7);
		}
		return rowNum + 1;
	}

	private int definitionStructure(Structure structure, XSSFWorkbook workbook, XSSFSheet sheet, int rowNum) {
		final int HEADER_SIZE = 5;
		XSSFRow row;
		XSSFCell cell;

		// set header
		row = createRow(sheet, rowNum);
		cell = createCell(row, "구조물", 0);
		setBoldStyle(workbook, cell, BOLD_FONT_SIZE);
		//TODO merge Section
		sheet.addMergedRegion(new CellRangeAddress(rowNum, rowNum, 0, HEADER_SIZE - 1));
		++rowNum;

		row = createRow(sheet, rowNum++);
		createCell(row, "id", 0);
		createCell(row, "height", 1);
		createCell(row, "done", 2);
		createCell(row, "location", 3);
		createCell(row, "comment", 4);
		for (int i = 0; i < HEADER_SIZE; ++i) {
			setBoldStyle(workbook, row.getCell(i), FONT_SIZE);
		}

		//set cell contents
		row = createRow(sheet, rowNum++);
		createCell(row, structure.getId().toString(), 0);
		createCell(row, structure.getHeight().toString(), 1);
		createCell(row, structure.getIsWorkDone().toString(), 2);
		createCell(row, structure.getLocation().getLocationDetail(), 3);
		createCell(row, structure.getComment(), 4);

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
		final int HEADER_SIZE = 5;
		XSSFRow row;
		XSSFCell cell;

		//set sheet title
		row = createRow(sheet, 0);
		cell = createCell(row, project.getName(), 0);
		setBoldStyle(workbook, cell, BOLD_FONT_SIZE);
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, HEADER_SIZE - 1));

		//set header
		row = createRow(sheet, 1);

		createCell(row, "id", 0);
		createCell(row, "x", 1);
		createCell(row, "y", 2);
		createCell(row, "location", 3);
		createCell(row, "comment", 4);
		for (int i = 0; i < HEADER_SIZE; ++i) {
			setBoldStyle(workbook, row.getCell(i), FONT_SIZE);
		}

		//set cell contents
		row = createRow(sheet, 2);
		createCell(row, project.getId().toString(), 0);
		createCell(row, project.getLocation().getLocationX().toString(), 1);
		createCell(row, project.getLocation().getLocationY().toString(), 2);
		createCell(row, project.getLocation().getLocationDetail(), 3);
		createCell(row, project.getComment(), 4);

		return 4;
	}

	private String createFileName(Long id) {
		String fileName = id + "_"  + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));

		String encName = "null";
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
