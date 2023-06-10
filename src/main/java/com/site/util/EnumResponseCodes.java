package com.site.util;

public enum EnumResponseCodes {
	

	ER101("ER101", "user Detais is not save "),
	ER102("ER102", "user Detais is not update");
	

	private String code;
	private String message;

	private EnumResponseCodes(String code, String message) {
		this.code = code;
		this.message = message;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}