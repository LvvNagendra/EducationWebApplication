package com.site.registration;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lowagie.text.pdf.PdfPCell;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class FormModel {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "STUDENT_ID")
	private long fId;
	@Column(name = "STUDENT_NAME")
	private String studentName;
	@Column(name = "STUDENT_EMAILADDRESS")
	private String studentEmail;
	@Column(name = "STUDENT_PHONENUMER")
	private String studentPhoneNumber;
	@Column(name = "SELECT_BRANCH")
	private String selectBranch;
	@Column(name = "SELECT_COURSE")
	private String selectCourse;
	@Column(name = "COURSE_DURATION")
	private String courseDuration;
	@Column(name = "SELECT_ONLINE_OR_OFLINE")
	private String selectOnlineOrOffline;
	@Column(name = "COURSE_START_DATE_TIME")
	@DateTimeFormat(pattern= "yyyy-MM-dd HH:mm:ss")
	private String startDateAndTime;
	@Column(name = "COURSE_END_TIME")
	@DateTimeFormat(pattern= "yyyy-MM-dd HH:mm:ss")
	private String courseEndDate;
	@Column(name = "UPDATE_DETAILS_DATE")
	private Date updateDate;
	@Column(name = "STUDENT_REGISTRATIN_DATE")
	private Date registrationDate;
	@Column(name = "COURSE_PAYMENT_DETAILS")
	private double fees; 
	 
	

}
