package com.nju.edu.cn.software.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;
import com.nju.edu.cn.software.helper.Constant;
import com.nju.edu.cn.software.util.CustomExceptionUtil;

@Provider
public class CustomBadRequestException implements ExceptionMapper<UnrecognizedPropertyException > {
	@Override
	public Response toResponse(UnrecognizedPropertyException  e) {
		// TODO Auto-generated method stub
	
		return toResponse(e.getLocalizedMessage());
	}
	
	public static Response toResponse(String errorMsg){
		final String msg = Constant.BAD_REQUEST+errorMsg;
		final int code = Status.BAD_REQUEST.getStatusCode();
		return  CustomExceptionUtil.toResponse(code, msg);
	}

	public static Response toResponseInvalidParams(){
		final String msg = Constant.BAD_REQUEST+"参数不合法";
		final int code = Status.BAD_REQUEST.getStatusCode();
		return  CustomExceptionUtil.toResponse(code, msg);
	}
}
