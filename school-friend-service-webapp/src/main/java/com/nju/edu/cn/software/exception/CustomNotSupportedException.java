package com.nju.edu.cn.software.exception;

import javax.ws.rs.NotSupportedException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.nju.edu.cn.software.helper.Constant;
import com.nju.edu.cn.software.util.CustomExceptionUtil;

@Provider
public class CustomNotSupportedException implements ExceptionMapper<NotSupportedException> {
	
	@Override
	public Response toResponse(NotSupportedException e) {
	 
		return toResponse(e.getLocalizedMessage());
	}
	
	public static Response toResponse(String errorMsg) {
		// TODO Auto-generated method stub
		final String msg = Constant.NOT_SUPPORT+errorMsg;
		final int code = 415;
		return  CustomExceptionUtil.toResponse(code, msg);
		 
	}
}
