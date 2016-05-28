package com.nju.edu.cn.software.exception;

import javax.ws.rs.ForbiddenException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.nju.edu.cn.software.helper.Constant;
import com.nju.edu.cn.software.util.CustomExceptionUtil;

@Provider
public class CustomForbiddenException implements ExceptionMapper<ForbiddenException> {
	@Override
	public Response toResponse(ForbiddenException e) {
	 
		final String msg = Constant.FORBIDDEN_REQUEST_ERROR+e.getLocalizedMessage();
		final int code = Status.FORBIDDEN.getStatusCode();
		return  CustomExceptionUtil.toResponse(code, msg);
	}
}
