package com.nju.edu.cn.software.exception;

import javax.ws.rs.InternalServerErrorException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
 

import com.nju.edu.cn.software.helper.Constant;
import com.nju.edu.cn.software.util.CustomExceptionUtil;

@Provider
public class CustomInternalServerErrorException implements ExceptionMapper<InternalServerErrorException> {
	@Override
	public Response toResponse(InternalServerErrorException e) {
	 
		final String msg = Constant.INTERNAL_SERVER_ERROR+e.getLocalizedMessage();
		final int code = Status.INTERNAL_SERVER_ERROR.getStatusCode();
		return  CustomExceptionUtil.toResponse(code, msg);
	 
	}
}
