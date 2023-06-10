package com.site.dto;

import java.util.Date;

import lombok.Data;
import lombok.ToString;
@Data
@ToString
public class FormDto {
	private long fId;
	private String studentName;
	private String studentEmail;
	private String studentPhoneNumber;
	private String selectBranch;
	private String selectCourse;
	private String courseDuration;
	private String selectOnlineOrOffline;
	private String startDateAndTime;
	private Date updateDate;
	private String courseEndDate;
	private Date registrationDate;
	private double fees; 
	 

}
