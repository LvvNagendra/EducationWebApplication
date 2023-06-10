package com.site.service;

import java.io.FileNotFoundException;
import java.util.List;

import org.springframework.http.ResponseEntity;

import com.site.dto.FormDto;
import com.site.registration.FormModel;

import net.sf.jasperreports.engine.JRException;

public interface FormService {
	public ResponseEntity<?>  registerForm(FormDto request);
	
	public ResponseEntity<?>  editRegistrationForm(FormDto request);
	
	public List<FormDto> getAllStudentDetails();
	
	public FormModel deleteForm(long fId);

	/* public List<FormModel> getAllStudentById(long fId); */
	public FormModel getById(long fId);
	List<FormModel> findSingelUserByfId(long fId);

	public String exportReport(String reportFormat) throws JRException, FileNotFoundException;

}
