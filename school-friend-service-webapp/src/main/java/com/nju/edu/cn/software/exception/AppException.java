package com.nju.edu.cn.software.exception;

import com.nju.edu.cn.software.resource.StatusInfo;

public class AppException extends Throwable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int status;
	private int code;
	private String message;
	private StatusInfo statusInfo;
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public StatusInfo getStatusInfo() {
		return statusInfo;
	}
	public void setStatusInfo(StatusInfo statusInfo) {
		this.statusInfo = statusInfo;
	}
	
	
}
