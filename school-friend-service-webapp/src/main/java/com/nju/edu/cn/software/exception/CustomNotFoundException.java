package com.nju.edu.cn.software.exception;

import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.nju.edu.cn.software.helper.Constant;
import com.nju.edu.cn.software.util.CustomExceptionUtil;

@Provider
public class CustomNotFoundException implements ExceptionMapper<NotFoundException> {
	@Override
	public Response toResponse(NotFoundException e) {
	 
		final String msg = Constant.NOT_FOUND+e.getLocalizedMessage();
		final int code = Status.NOT_FOUND.getStatusCode();
		return  CustomExceptionUtil.toResponse(code, msg);
	}
}
