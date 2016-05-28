package com.nju.edu.cn.software.exception;

import javax.ws.rs.BadRequestException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.nju.edu.cn.software.helper.Constant;
import com.nju.edu.cn.software.util.CustomExceptionUtil;

@Provider
public class CustomBadRequestExceptionNull implements ExceptionMapper<BadRequestException > {
	@Override
	public Response toResponse(BadRequestException  e) {
		// TODO Auto-generated method stub
		return toResponse(e.getLocalizedMessage());
	}
	
	public static Response toResponse(String errorMsg){
		final String msg = Constant.BAD_REQUEST+errorMsg;
		final int code = Status.BAD_REQUEST.getStatusCode();
		return  CustomExceptionUtil.toResponse(code, msg);
	}
	
	
}
