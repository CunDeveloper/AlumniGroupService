package com.nju.edu.cn.software.exception;

import com.nju.edu.cn.software.helper.Constant;

public class BadClientCredentialsException extends BaseWebApplicationException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public BadClientCredentialsException(String developerMessage) {
		super(Constant.CREDENTIALS_INCORRECT, Constant.CREDENTIALS_INCORRECT_MSG, developerMessage);
		// TODO Auto-generated constructor stub
	}

}
