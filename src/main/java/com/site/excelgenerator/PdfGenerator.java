package com.site.excelgenerator;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.CMYKColor;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.site.dto.FormDto;
import com.site.registration.FormModel;

public class PdfGenerator {
	public void generate(List<FormDto> list, HttpServletResponse response) throws DocumentException, IOException {
	    // Creating the Object of Document
	    Document document = new Document(PageSize.A4);
	    // Getting instance of PdfWriter
	    PdfWriter.getInstance(document, response.getOutputStream());
	    // Opening the created document to change it
	    document.open();
	    // Creating font
	    // Setting font style and size
	    Font fontTiltle = FontFactory.getFont(FontFactory.TIMES_ROMAN);
	    fontTiltle.setSize(20);
	    // Creating paragraph
	    Paragraph paragraph1 = new Paragraph("List of All registration Forms", fontTiltle);
	    // Aligning the paragraph in the document
	    paragraph1.setAlignment(Paragraph.ALIGN_CENTER);
	    // Adding the created paragraph in the document
	    document.add(paragraph1);
	    // Creating a table of the 14 columns
	    PdfPTable table = new PdfPTable(13);
	    // Setting width of the table, its columns and spacing
	    table.setWidthPercentage(120 );
	    table.setWidths(new int[] {5,5,5,5,5,5,5,5,5,5,5,5,5});
	    table.setSpacingBefore(5);
	    // Create Table Cells for the table header
	    PdfPCell cell = new PdfPCell();
	    // Setting the background color and padding of the table cell
	    cell.setBackgroundColor(CMYKColor.BLUE);
	    cell.setPadding(5);
	    // Creating font
	    // Setting font style and size
	    Font font = FontFactory.getFont(FontFactory.TIMES_ROMAN);
	    font.setColor(CMYKColor.WHITE);
	    // Adding headings in the created table cell or  header
	    // Adding Cell to table
	    cell.setPhrase(new Phrase("Fid", font));
	    table.addCell(cell);
	    cell.setPhrase(new Phrase("Student Name", font));
	    table.addCell(cell);
	    cell.setPhrase(new Phrase("studentEmail", font));
	    table.addCell(cell);
	    cell.setPhrase(new Phrase("studentPhoneNumber", font));
	    table.addCell(cell);
	    cell.setPhrase(new Phrase("selectBranch", font));
	    table.addCell(cell);
	    cell.setPhrase(new Phrase("selectCourse", font));
	    table.addCell(cell);
	    cell.setPhrase(new Phrase("courseDuration", font));
	    table.addCell(cell);
	    cell.setPhrase(new Phrase("selectOnlineOrOffline", font));
	    table.addCell(cell);
	    cell.setPhrase(new Phrase("startDateAndTime", font));
	    table.addCell(cell);
	    cell.setPhrase(new Phrase("courseEndDate", font));
	    table.addCell(cell); 
	    cell.setPhrase(new Phrase("updateDate", font));
	    table.addCell(cell); 
	    cell.setPhrase(new Phrase("registrationDate", font));
	    table.addCell(cell);
	    cell.setPhrase(new Phrase("fees", font));
	    table.addCell(cell);
	    
	    // Iterating the list of students
	    for (FormDto student: list) {
	      // Adding student id
	      table.addCell(String.valueOf(student.getFId()));
	      // Adding student name
	      table.addCell(student.getStudentName());
	      // Adding student email
	      table.addCell(student.getStudentEmail());
	      // Adding student mobile
	      table.addCell(student.getStudentPhoneNumber());
	   // Adding student name
	      table.addCell(student.getSelectBranch());
	   // Adding student name
	      
	      table.addCell(student.getSelectCourse());
	   // Adding student name
	      table.addCell(student.getCourseDuration());
	   // Adding student name
	      table.addCell(student.getSelectOnlineOrOffline());
	   // Adding student name
	      table.addCell(student.getStartDateAndTime());
	      
	      table.addCell(student.getCourseEndDate());
	      table.addCell(String.valueOf(student.getUpdateDate()));
	     table.addCell(String.valueOf(student.getRegistrationDate()));
	      table.addCell(String.valueOf(student.getFees()));
	     
	    }
	    // Adding the created table to the document
	    document.add(table);
	    // Closing the document
	    document.close();
	  }
	public void generates(List<FormModel> list, HttpServletResponse response) throws DocumentException, IOException {
		/* List<FormModel> li=new ArrayList<FormModel>(); */
	    // Creating the Object of Document
	    Document document = new Document(PageSize.A4);
	    // Getting instance of PdfWriter
	    PdfWriter.getInstance(document, response.getOutputStream());
	    // Opening the created document to change it
	    document.open();
	    // Creating font
	    // Setting font style and size
	    Font fontTiltle = FontFactory.getFont(FontFactory.TIMES_ROMAN);
	    fontTiltle.setSize(20);
	    // Creating paragraph
	    Paragraph paragraph1 = new Paragraph("UserIdBy  registration Form", fontTiltle);
	    // Aligning the paragraph in the document
	    paragraph1.setAlignment(Paragraph.ALIGN_CENTER);
	    // Adding the created paragraph in the document
	    document.add(paragraph1);
	    // Creating a table of the 14 columns
	    PdfPTable table = new PdfPTable(13);
	    // Setting width of the table, its columns and spacing
	    table.setWidthPercentage(100 );
	    table.setWidths(new int[] {5,5,5,5,5,5,5,5,5,5,5,5,5});
	    table.setSpacingBefore(5);
	    // Create Table Cells for the table header
	    PdfPCell cell = new PdfPCell();
	    // Setting the background color and padding of the table cell
	    cell.setBackgroundColor(CMYKColor.BLUE);
	    cell.setPadding(5);
	    // Creating font
	    // Setting font style and size
	    Font font = FontFactory.getFont(FontFactory.TIMES_ROMAN);
	    font.setColor(CMYKColor.WHITE);
	    // Adding headings in the created table cell or  header
	    // Adding Cell to table
	    cell.setPhrase(new Phrase("Fid", font));
	    table.addCell(cell);
	    cell.setPhrase(new Phrase("Student Name", font));
	    table.addCell(cell);
	    cell.setPhrase(new Phrase("studentEmail", font));
	    table.addCell(cell);
	    cell.setPhrase(new Phrase("studentPhoneNumber", font));
	    table.addCell(cell);
	    cell.setPhrase(new Phrase("selectBranch", font));
	    table.addCell(cell);
	    cell.setPhrase(new Phrase("selectCourse", font));
	    table.addCell(cell);
	    cell.setPhrase(new Phrase("courseDuration", font));
	    table.addCell(cell);
	    cell.setPhrase(new Phrase("selectOnlineOrOffline", font));
	    table.addCell(cell);
	    cell.setPhrase(new Phrase("startDateAndTime", font));
	    table.addCell(cell);
	    cell.setPhrase(new Phrase("courseEndDate", font));
	    table.addCell(cell); 
	    cell.setPhrase(new Phrase("updateDate", font));
	    table.addCell(cell); 
	    cell.setPhrase(new Phrase("registrationDate", font));
	    table.addCell(cell);
	    cell.setPhrase(new Phrase("fees", font));
	    table.addCell(cell);
	    
	    // Iterating the list of students
	    for (FormModel student: list) {
	      // Adding student id
	      table.addCell(String.valueOf(student.getFId()));
	      // Adding student name
	      table.addCell(student.getStudentName());
	      // Adding student email
	      table.addCell(student.getStudentEmail());
	      // Adding student mobile
	      table.addCell(student.getStudentPhoneNumber());
	   // Adding student name
	      table.addCell(student.getSelectBranch());
	   // Adding student name
	      
	      table.addCell(student.getSelectCourse());
	   // Adding student name
	      table.addCell(student.getCourseDuration());
	   // Adding student name
	      table.addCell(student.getSelectOnlineOrOffline());
	   // Adding student name
	      table.addCell(student.getStartDateAndTime());
	      
	      table.addCell(student.getCourseEndDate());
	      table.addCell(String.valueOf(student.getUpdateDate()));
	     table.addCell(String.valueOf(student.getRegistrationDate()));
	      table.addCell(String.valueOf(student.getFees()));
	     
	    }
	    // Adding the created table to the document
	    document.add(table);
	    // Closing the document
	    document.close();
	  }
	

}
