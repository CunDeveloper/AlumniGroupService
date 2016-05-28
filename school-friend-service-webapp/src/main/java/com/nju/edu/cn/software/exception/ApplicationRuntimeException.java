package com.nju.edu.cn.software.exception;

import com.nju.edu.cn.software.helper.Constant;

public class ApplicationRuntimeException extends BaseWebApplicationException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ApplicationRuntimeException(String developerMessage) {
		super(Constant.INTERNAL_SYSTEM_ERROR_CODE,Constant.INTERNAL_SYSTEM_ERROR_MSG, developerMessage);
		// TODO Auto-generated constructor stub
	}

}
