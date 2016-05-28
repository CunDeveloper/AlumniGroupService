package com.nju.edu.cn.software.exception;

import javax.ws.rs.NotAllowedException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.nju.edu.cn.software.helper.Constant;
import com.nju.edu.cn.software.util.CustomExceptionUtil;

@Provider
public class CustomNotAllowedException implements ExceptionMapper<NotAllowedException> {
	@Override
	public Response toResponse(NotAllowedException e) {
 
		final String msg = Constant.METHOD_NOT_ALLOWED+e.getLocalizedMessage();
		final int code = Status.METHOD_NOT_ALLOWED.getStatusCode();
		return  CustomExceptionUtil.toResponse(code, msg);
	}
}
