package com.nju.edu.cn.software.exception;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.nju.edu.cn.software.helper.Constant;
import com.nju.edu.cn.software.util.CustomExceptionUtil;

@Provider
public class CustomWebApplicationException implements ExceptionMapper<WebApplicationException> {
	@Override
	public Response toResponse(WebApplicationException e) {
	 
		final String msg = Constant.WEB_APPLICATION_ERROR+e.getLocalizedMessage();
		final int code = 500;
		return  CustomExceptionUtil.toResponse(code, msg);
	}
}
