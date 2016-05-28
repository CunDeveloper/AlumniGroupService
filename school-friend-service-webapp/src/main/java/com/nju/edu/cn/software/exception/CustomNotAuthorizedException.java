package com.nju.edu.cn.software.exception;
 

import javax.ws.rs.NotAuthorizedException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.nju.edu.cn.software.helper.Constant;
import com.nju.edu.cn.software.util.CustomExceptionUtil;
 

@Provider
public class CustomNotAuthorizedException implements ExceptionMapper<NotAuthorizedException> {
	@Override
	public Response toResponse(NotAuthorizedException e) {
 
		return toResponse(e.getLocalizedMessage());
	}
	
	public static Response toResponse(final String errorMsg) {
		// TODO Auto-generated method stub
		final String msg = Constant.NOT_AUTHORIZED+errorMsg;
		final int code = Status.UNAUTHORIZED.getStatusCode();
		return  CustomExceptionUtil.toResponse(code, msg);
	}
}
