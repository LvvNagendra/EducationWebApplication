package com.site.excelgenerator;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.site.dto.FormDto;
import com.site.registration.FormModel;

public class ExcelGenerator {
	public static ByteArrayInputStream shipToExcel(List<FormDto> model) throws IOException
	{
		String[] columns = {"fId","studentName","studentEmail","phoneNumber","selectCourse","selectBranch","courseDuration","selectOnlineOrOffline","startDateAndTime","courseEndDate","updateDate","registrationDate","fees"};
	
		try(
			Workbook workbook = new XSSFWorkbook();
				
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			){
			Sheet sheet = workbook.createSheet("ShipReport");
			
			Font headerFont = workbook.createFont();
			headerFont.setBold(true);
			headerFont.setColor(IndexedColors.BLUE.getIndex());
			
			CellStyle headerCellStyle = workbook.createCellStyle();
			headerCellStyle.setFont(headerFont);
			
			//Row for Header-->
			Row headerRow = sheet.createRow(0);
			
			//Header
			for(int col=0; col<columns.length; col++)
			{
				Cell cell = headerRow.createCell(col);
				cell.setCellValue(columns[col]);
				cell.setCellStyle(headerCellStyle);
			}
			
			int rowIdx =1;
			for(FormDto ship: model)
			{
				Row row = sheet.createRow(rowIdx++);
				
				row.createCell(0).setCellValue(ship.getFId());
				row.createCell(1).setCellValue(ship.getStudentName());
				row.createCell(2).setCellValue(ship.getStudentEmail());
				row.createCell(3).setCellValue(ship.getStudentPhoneNumber());
				row.createCell(4).setCellValue(ship.getSelectCourse());
				row.createCell(5).setCellValue(ship.getSelectBranch());
				row.createCell(6).setCellValue(ship.getCourseDuration());
				row.createCell(7).setCellValue(ship.getSelectOnlineOrOffline());
				row.createCell(8).setCellValue(ship.getStartDateAndTime());
				row.createCell(9).setCellValue(ship.getCourseEndDate());
				row.createCell(10).setCellValue(ship.getUpdateDate());
				row.createCell(11).setCellValue(ship.getRegistrationDate());
				row.createCell(12).setCellValue(ship.getFees());


			}
			workbook.write(out);
			return new ByteArrayInputStream(out.toByteArray());
		}
	}
	
	
	
	

}
