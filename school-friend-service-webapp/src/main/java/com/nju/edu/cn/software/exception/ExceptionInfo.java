package com.nju.edu.cn.software.exception;

public class ExceptionInfo {

	private int code;
	private String msg;
	
	public ExceptionInfo(){
		
	}
	
	public ExceptionInfo(int code, String msg) {
		super();
		this.code = code;
		this.msg = msg;
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
}
