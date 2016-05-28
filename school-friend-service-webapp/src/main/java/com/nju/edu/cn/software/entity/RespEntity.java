package com.nju.edu.cn.software.entity;

public  class RespEntity<T> {

	private ExceptionInfo exception;
	private T info;
 
	public ExceptionInfo getException() {
		return exception;
	}

	public void setException(ExceptionInfo exception) {
		this.exception = exception;
	}

	public T getInfo() {
		return info;
	}

	public void setInfo(T info) {
		this.info = info;
	}
 
}
