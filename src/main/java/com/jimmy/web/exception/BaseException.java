package com.jimmy.web.exception;

public class BaseException extends RuntimeException {

	/**
	 * 自动生成serialId
	 */
	private static final long serialVersionUID = 8728157739395418674L;
	
	private String code;
	private String message;
	private String type;
	
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
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	
}
