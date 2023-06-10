package com.site.controller;

import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.util.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.lowagie.text.DocumentException;
import com.site.dto.FormDto;
import com.site.excelgenerator.ExcelGenerator;
import com.site.excelgenerator.PdfGenerator;
import com.site.registration.FormModel;
import com.site.repository.FormRepository;
import com.site.service.FormService;

import net.sf.jasperreports.engine.JRException;


@Controller
public class FormController {
	
	
	@Autowired
	private FormService  service;
	@Autowired
	private FormRepository formRepository;
	
	private static final Logger LOGGER = LogManager.getLogger(FormController.class);
	@GetMapping("/")
	public String home(Model m)
	{
		LOGGER.info("Executing :: FormController.getAllUsers()");
		List<FormDto> formDto=service.getAllStudentDetails();
		m.addAttribute("formDto",formDto);
		
	   return "index";
	}
	
	
	@GetMapping("/addUser")
	public String addEmpForm()
	{
		return "user_register";
	}
	
	
	@PostMapping("/register")
	public String empRegister(@ModelAttribute FormDto formDto, HttpSession session)
	{
		LOGGER.info("Exiting :: FormController.formDto response):===> {}   \":{}" + formDto);
		
		FormModel model=formRepository.findByStudentEmail(formDto.getStudentEmail());
		try {
		if(model!=null) {
			session.setAttribute("msg", "User Already existed");
			return "redirect:/";
			
		}else {
		
		service.registerForm(formDto);
		session.setAttribute("msg", "User Added Succesfully");
		
		return "redirect:/";
		}} catch (Exception e) {
			// An error occurred while processing hiring request
			LOGGER.error("formDto request not accepted :{}", e.getMessage());
			session.setAttribute("msg", "User notAdded Succesfully");
			return "redirect:/";
		}
	}
	
	
	@GetMapping("/edit/{fId}")
	public String edit(@PathVariable long fId,Model m,HttpSession session)
	{
		LOGGER.info("Executing :: FormController.edit(int id)" + fId);
		FormModel formModel=formRepository.getUserByfId(fId);
		if(formModel!=null) {
		 formModel=service.getById(fId);
		m.addAttribute("formModel",formModel);
		return "edit";
		}else {
			session.setAttribute("msg", "User Id Is NotExisted");
			return "redirect:/";	
			
		}
		
	}
	@PostMapping("/update")
	public String updateUser(@ModelAttribute FormDto formDto,HttpSession session)
	{
		LOGGER.info("Exiting :: FormController.formDto response):===> {}   \":{}" + formDto);
		try {
		service.editRegistrationForm(formDto);
		session.setAttribute("msg", "Form updated Succesfully");
		return "redirect:/";
		}catch(Exception e) {
			
			session.setAttribute("msg", "Form notUpdated Succesfully");
			return "redirect:/";	
		}
	}
	@GetMapping("/delete/{fId}")
	public String deleteUser(@PathVariable long fId,HttpSession session)
	{
		LOGGER.info("Executing :: FormController.deleteByUserId(int id)" + fId);
		try {
			service.deleteForm(fId);
			session.setAttribute("msg", "Form Deleted Succesfully");
			return "redirect:/";
		}catch(Exception e) {
			
			session.setAttribute("msg", "User Id Is NotExisted");
			return "redirect:/";	
		}
		
		
	}
	@GetMapping("/formReport/{format}")
	public String generateReport(@PathVariable String format) throws FileNotFoundException, JRException {
		return service.exportReport(format);
	}
	
	
	@GetMapping(value = "/formDetaToExcel", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
	public ResponseEntity<byte[]> excelShipReport() throws IOException {
	    List<FormDto> model = service.getAllStudentDetails();
	    ByteArrayInputStream in = ExcelGenerator.shipToExcel(model);

	    byte[] data = IOUtils.toByteArray(in);
	    HttpHeaders headers = new HttpHeaders();
	    headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
	    headers.setContentDisposition(ContentDisposition.attachment().filename("ShipReport.xlsx").build());

	    return new ResponseEntity<>(data, headers, HttpStatus.OK);
	}
	@GetMapping("/export-to-pdf")
	  public void generatePdfFile(HttpServletResponse response) throws DocumentException, IOException 
	  {
	    response.setContentType("application/pdf");
	    DateFormat dateFormat = new SimpleDateFormat("YYYY-MM-DD:HH:MM:SS");
	    String currentDateTime = dateFormat.format(new Date());
	    String headerkey = "Content-Disposition";
	    String headervalue = "attachment; filename=student" + currentDateTime + ".pdf";
	    response.setHeader(headerkey, headervalue);
		/* List < FormModel > list = service.getAllStudentDetailss(); */
	    List<FormDto> list=service.getAllStudentDetails();
	    PdfGenerator generator = new PdfGenerator();
	    generator.generate(list, response);
	  }
	
	
	@GetMapping("/pdfGetById/{fId}")
	  public void generatesPdfFile(HttpServletResponse response, @PathVariable("fId") long fId) throws DocumentException, IOException 
	  {
	    response.setContentType("application/pdf");
	    DateFormat dateFormat = new SimpleDateFormat("YYYY-MM-DD:HH:MM:SS");
	    String currentDateTime = dateFormat.format(new Date());
	    String headerkey = "Content-Disposition";
	    String headervalue = "attachment; filename=student" + currentDateTime + ".pdf";
	    response.setHeader(headerkey, headervalue);
	  List<FormModel> list =  service.findSingelUserByfId(fId);
	    PdfGenerator generator = new PdfGenerator();
	    generator.generates( list, response);
	  }
	
	@GetMapping("getById/{fId}")
	public String GetById(@PathVariable ("fId") long fId)
	{
		 service.findSingelUserByfId(fId);
		 return "index";
		
		
	}
	
	
}
