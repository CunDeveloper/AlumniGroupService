package com.nju.edu.cn.software.exception;

import javax.ws.rs.NotAcceptableException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.nju.edu.cn.software.helper.Constant;
import com.nju.edu.cn.software.util.CustomExceptionUtil;

@Provider
public class CustomNotAcceptableException implements ExceptionMapper<NotAcceptableException> {
	@Override
	public Response toResponse(NotAcceptableException e) {
	 
		final String msg = Constant.NOT_ACCEPTABLE_ERROR+e.getLocalizedMessage();
		final int code = Status.NOT_ACCEPTABLE.getStatusCode();
		return  CustomExceptionUtil.toResponse(code, msg);
	}
}
