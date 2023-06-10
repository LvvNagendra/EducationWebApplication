package com.site.serviceimpl;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import com.site.dto.ErrorResponseDto;
import com.site.dto.FormDto;
import com.site.registration.FormModel;
import com.site.repository.FormRepository;
import com.site.service.FormService;
import com.site.util.EnumResponseCodes;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;



@Service
public class FormServiceImpl implements FormService {
@Autowired
private FormRepository formRepository;
/*
 * @Value("${spring.mail.username}") private String sender;
 * 
 * @Value("${email.url}") private String emailUrl;
 */
@Autowired
EmailService emailService;
private static final Logger LOGGER = LoggerFactory.getLogger(FormServiceImpl.class);


	@Override
	public ResponseEntity<?>  registerForm(FormDto request) {
		ErrorResponseDto response=new ErrorResponseDto();
		
	FormModel formModel=null;
	try {
	formModel= new FormModel();
	formModel.setStudentName(request.getStudentName());
	formModel.setStudentEmail(request.getStudentEmail());
	formModel.setStudentPhoneNumber(request.getStudentPhoneNumber());
	formModel.setSelectBranch(request.getSelectBranch());
	formModel.setSelectCourse(request.getSelectCourse());
	formModel.setCourseDuration("three months");
	formModel.setRegistrationDate(new Date());
	formModel.setSelectOnlineOrOffline(request.getSelectOnlineOrOffline());
	formModel.setStartDateAndTime(request.getStartDateAndTime());
	formModel.setCourseEndDate(request.getCourseEndDate());
	/* formModel.setFees(request.getFees()); */
	formModel.setUpdateDate(request.getUpdateDate());
	String selectCourse=request.getSelectCourse();
	if (selectCourse.equalsIgnoreCase("CoreJava")) {
		formModel.setFees(7500);
	}
	if (selectCourse.equalsIgnoreCase("AdvanceJava")) {
		formModel.setFees(7000);
	}
	if (selectCourse.equalsIgnoreCase("webTtechnology")) {
		formModel.setFees(8000);
	}
	if (selectCourse.equalsIgnoreCase("Python")) {
		formModel.setFees(7500);
	}
	if (selectCourse.equalsIgnoreCase("JavaFullStack")) {
		formModel.setFees(13000);
	}
	if (selectCourse.equalsIgnoreCase("c")) {
		formModel.setFees(8000);
	}
	if (selectCourse.equalsIgnoreCase("c++")) {
		formModel.setFees(8500);
	}
	
	formRepository.save(formModel);
	if (formModel != null) {

		SimpleMailMessage mailMessage = new SimpleMailMessage();
		mailMessage.setFrom("lvvnagendra99@gmail.com");
		mailMessage.setTo(request.getStudentEmail());
		mailMessage.setSubject(" Registration Successful!!");
		mailMessage.setText("Welcome To online educationSite,  "
				 + formModel.getFId());
		

		emailService.sendEmail(mailMessage);
	}}catch (Exception e) {
		LOGGER.error("form  details are not saved in service layer request {} ", e);
		
		response.setResponseCode(EnumResponseCodes.ER101.getCode());
		response.setResponsemessage(EnumResponseCodes.ER101.getMessage());
	
	}
	return ResponseEntity.ok(response);
	

	}

	public ResponseEntity<?>  editRegistrationForm(FormDto request) {
		ErrorResponseDto response=new ErrorResponseDto();
		
		FormModel formModel=null;
		try {
		formModel= new FormModel();
		formModel=formRepository.getUserByfId(request.getFId());
		if(formModel!=null) {
		formModel.setStudentName(request.getStudentName());
		formModel.setStudentEmail(request.getStudentEmail());
		formModel.setStudentPhoneNumber(request.getStudentPhoneNumber());
		formModel.setSelectBranch(request.getSelectBranch());
		formModel.setSelectCourse(request.getSelectCourse());
		formModel.setSelectOnlineOrOffline(request.getSelectOnlineOrOffline());
		formModel.setStartDateAndTime(request.getStartDateAndTime());
		formModel.setFees(request.getFees());
		formModel.setUpdateDate(new Date());
		formRepository.save(formModel);
		
		}
		}catch (Exception e) {
			LOGGER.error("form  details are not updated in service layer request {} ", e);
			
			response.setResponseCode(EnumResponseCodes.ER102.getCode());
			response.setResponsemessage(EnumResponseCodes.ER102.getMessage());
		
		}
		return ResponseEntity.ok(response);

	}

	public List<FormDto> getAllStudentDetails() {
		
		List<FormModel> list=formRepository.findAll();
		List<FormDto> dtoList= new ArrayList<FormDto>();
		for(FormModel formModel :list) {
		
			FormDto request= new FormDto();
		request.setFId(formModel.getFId());
		request.setStudentName(formModel.getStudentName());
		request.setStudentEmail(formModel.getStudentEmail());
		request.setStudentPhoneNumber(formModel.getStudentPhoneNumber());
		request.setSelectBranch(formModel.getSelectBranch());
		request.setCourseDuration(formModel.getCourseDuration());
		request.setSelectCourse(formModel.getSelectCourse());
		request.setRegistrationDate(formModel.getRegistrationDate());
		request.setSelectOnlineOrOffline(formModel.getSelectOnlineOrOffline());
		request.setStartDateAndTime(formModel.getStartDateAndTime());
		request.setUpdateDate(formModel.getUpdateDate());
		request.setCourseEndDate(formModel.getCourseEndDate());
		request.setFees(formModel.getFees());
		dtoList.add(request);
		
		}
		return dtoList;
		
	}

	public FormModel deleteForm(long fId) {
		
		 formRepository.deleteById(fId);
		return null;
		 
	}

	@Override
	public FormModel getById(long fId) {
		
			  Optional<FormModel>e=formRepository.findById(fId);
			  
			  if(e.isPresent())
			  {
				  return e.get();
			  }
			  return null;
			}

	
	@Override
	public String exportReport(String reportFormat) throws JRException, FileNotFoundException {
		
		String path = "D:\\ShipReport";
				
		List<FormModel> formModel = formRepository.findAll();

		File file = ResourceUtils.getFile("classpath:reports\\ShipReports.jrxml");
		JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
		JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(formModel);
		
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("createdBy", "");
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
		
		//Create for HTML
		if(reportFormat.equalsIgnoreCase("html")) {
			JasperExportManager.exportReportToHtmlFile(jasperPrint, path +"\\Form_File.html");
		}
		
		//Create for PDF
		if(reportFormat.equalsIgnoreCase("pdf")) {
					JasperExportManager.exportReportToPdfFile(jasperPrint, path +"\\Form_File.pdf");
				}
				
		//Create for XML
		if(reportFormat.equalsIgnoreCase("xml")) {
			JasperExportManager.exportReportToXmlFile(jasperPrint, path +"\\Form_File.xml",false);
		}
		
		return "Report generated in path :"+ path;
		}

	@Override
	public List<FormModel> findSingelUserByfId(long fId) {
		
		  List<FormModel> list=formRepository.findSingelUserByfId(fId);
		  return list;
	}

		/*
		 * @Override public List<FormModel> getAllStudentById(long fId) {
		 * 
		 * @SuppressWarnings("unchecked") List<FormModel> lists=(List<FormModel>)
		 * formRepository.getUserByfId(fId); return lists; }
		 */
	
}
