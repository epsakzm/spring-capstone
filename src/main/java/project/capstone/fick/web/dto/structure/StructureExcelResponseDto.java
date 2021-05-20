package project.capstone.fick.web.dto.structure;

import lombok.Getter;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.springframework.util.ObjectUtils;
import project.capstone.fick.domain.structure.Structure;

import java.lang.reflect.Field;
import java.util.Optional;
import java.util.OptionalDouble;

@Getter
public class StructureExcelResponseDto {
	public String id;

	public String height;

	public String is_done;

	public String location_x;

	public String location_y;

	public String comment;

	private String name;

	public StructureExcelResponseDto(Structure structure) {
		this.id = Optional.ofNullable(structure.getId()).orElse(-1L).toString();
		this.height = Optional.ofNullable(structure.getHeight()).orElse(-1D).toString();
		this.is_done = Optional.ofNullable(structure.getIsWorkDone()).orElse(false).toString();
		this.location_x = Optional.ofNullable(structure.getLocation().getLocationX()).orElse(-1D).toString();
		this.location_y = Optional.ofNullable(structure.getLocation().getLocationY()).orElse(-1D).toString();
		this.comment = Optional.ofNullable(structure.getComment()).orElse("NULL");
		this.name = Optional.ofNullable(structure.getName()).orElse("NULL");
	}

	public void createStructureCells(XSSFRow row) {
		int idx = 0;

		row.createCell(idx++).setCellValue(getId());
		row.createCell(idx++).setCellValue(getHeight());
		row.createCell(idx++).setCellValue(getIs_done());
		row.createCell(idx++).setCellValue(getLocation_x());
		row.createCell(idx++).setCellValue(getLocation_y());
		row.createCell(idx).setCellValue(getComment());
	}

	public void createStructureHeaderCells(XSSFRow row) {
		Field[] fields = this.getClass().getFields();
		for (int i = 0; i < fields.length; ++i) {
			row.createCell(i).setCellValue(fields[i].getName());
		}
	}
}
