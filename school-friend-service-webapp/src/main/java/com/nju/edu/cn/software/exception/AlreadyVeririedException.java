package com.nju.edu.cn.software.exception;

import com.nju.edu.cn.software.helper.Constant;

public class AlreadyVeririedException extends BaseWebApplicationException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public AlreadyVeririedException(String developerMessage) {
		super(Constant.ALREADY_VERIRIED,Constant.ALREADY_VERIRIED_MSG,developerMessage);
		// TODO Auto-generated constructor stub
	}

}
