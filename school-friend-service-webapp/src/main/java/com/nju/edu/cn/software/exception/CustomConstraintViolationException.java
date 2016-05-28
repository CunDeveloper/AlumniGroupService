package com.nju.edu.cn.software.exception;

import javax.validation.ConstraintViolationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.nju.edu.cn.software.helper.Constant;
import com.nju.edu.cn.software.util.CustomExceptionUtil;

@Provider
public class CustomConstraintViolationException implements ExceptionMapper<ConstraintViolationException> {
	@Override
	public Response toResponse(ConstraintViolationException e) {
	 
		final String msg = Constant.BAD_REQUEST+e.getLocalizedMessage();
		final int code = 400;
		return  CustomExceptionUtil.toResponse(code, msg);
	}
}
