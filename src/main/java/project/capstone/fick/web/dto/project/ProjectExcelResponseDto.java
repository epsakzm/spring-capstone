package project.capstone.fick.web.dto.project;

import lombok.Getter;
import org.apache.poi.xssf.usermodel.XSSFRow;
import project.capstone.fick.domain.project.Project;

import java.lang.reflect.Field;
import java.util.Optional;

@Getter
public class ProjectExcelResponseDto {

	public String id;

	public String location_x;

	public String location_y;

	public String comment;

	private String name;

	public ProjectExcelResponseDto(Project project) {
		this.id = Optional.ofNullable(project.getId()).orElse(-1L).toString();
		this.location_x = Optional.ofNullable(project.getLocation().getLocationX()).orElse(-1D).toString();
		this.location_y = Optional.ofNullable(project.getLocation().getLocationY()).orElse(-1D).toString();
		this.comment = Optional.ofNullable(project.getComment()).orElse("NULL");
		//private
		this.name = Optional.ofNullable(project.getName()).orElse("NULL");
	}

	public void createProjectHeaderCells(XSSFRow row) {
		Field[] fields = this.getClass().getFields();
		for (int i = 0; i < fields.length; ++i) {
			row.createCell(i).setCellValue(fields[i].getName());
		}
	}

	public void createProjectCells(XSSFRow row) {
		int idx = 0;
		row.createCell(idx++).setCellValue(getId());
		row.createCell(idx++).setCellValue(getLocation_x());
		row.createCell(idx++).setCellValue(getLocation_y());
		row.createCell(idx).setCellValue(getComment());
	}
}
