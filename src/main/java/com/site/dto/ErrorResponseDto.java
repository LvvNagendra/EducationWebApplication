package com.site.dto;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class ErrorResponseDto {
	String responseCode;
	String responsemessage;
	
}
