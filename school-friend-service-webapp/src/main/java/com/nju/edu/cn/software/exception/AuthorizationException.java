package com.nju.edu.cn.software.exception;

import com.nju.edu.cn.software.helper.Constant;

public class AuthorizationException extends BaseWebApplicationException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public AuthorizationException(String developerMessage) {
		super(Constant.NOT_AUTHORIZED_CODE,Constant.NOT_AUTHORIZED_MSG, developerMessage);
		// TODO Auto-generated constructor stub
	}

}
