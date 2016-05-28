package com.nju.edu.cn.software.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.fasterxml.jackson.core.JsonParseException;
import com.nju.edu.cn.software.helper.Constant;
import com.nju.edu.cn.software.util.CustomExceptionUtil;

@Provider
public class CustomJsonParseException implements ExceptionMapper<JsonParseException> {
	@Override
	public Response toResponse(JsonParseException e) {
		  
		final String msg = Constant.NOT_FOUND+e.getLocalizedMessage();
		final int code = 500;
		return  CustomExceptionUtil.toResponse(code, msg);
	}
}
