package project.capstone.fick.web.dto.crack;

import lombok.Getter;
import org.apache.poi.xssf.usermodel.XSSFRow;
import project.capstone.fick.domain.crack.Crack;
import project.capstone.fick.domain.crack.CrackRiskLevel;

import java.lang.reflect.Field;
import java.util.Optional;

@Getter
public class CrackExcelResponseDto {

	public String id;

	public String width;

	public String height;

	public String location_x;

	public String location_y;

	public String risk_level;

	public String comment;

	public CrackExcelResponseDto(Crack crack) {
		this.id = Optional.ofNullable(crack.getId()).orElse(-1L).toString();
		this.width = optionalDoubleValue(crack.getWidth());
		this.height = optionalDoubleValue(crack.getHeight());
		this.location_x = optionalDoubleValue(crack.getLocation().getLocationX());
		this.location_y = optionalDoubleValue(crack.getLocation().getLocationY());
		this.risk_level = Optional.ofNullable(crack.getRiskLevel()).orElse(CrackRiskLevel.HIGH).toString();
		this.comment = Optional.ofNullable(crack.getComment()).orElse("NULL");
	}

	private String optionalDoubleValue(Object value) {
		return Optional.ofNullable(value).orElse(-1D).toString();
	}

	public void createCrackCells(XSSFRow row) {
		int idx = 0;
		row.createCell(idx++).setCellValue(getId());
		row.createCell(idx++).setCellValue(getWidth());
		row.createCell(idx++).setCellValue(getHeight());
		row.createCell(idx++).setCellValue(getLocation_x());
		row.createCell(idx++).setCellValue(getLocation_y());
		row.createCell(idx++).setCellValue(getRisk_level());
		row.createCell(idx).setCellValue(getComment());
	}

}
