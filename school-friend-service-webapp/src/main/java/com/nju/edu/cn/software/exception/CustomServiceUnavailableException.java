package com.nju.edu.cn.software.exception;

import javax.naming.ServiceUnavailableException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.nju.edu.cn.software.helper.Constant;
import com.nju.edu.cn.software.util.CustomExceptionUtil;

@Provider
public class CustomServiceUnavailableException implements ExceptionMapper<ServiceUnavailableException> {
	@Override
	public Response toResponse(ServiceUnavailableException e) {
	 
		final String msg = Constant.SERVER_UNAAVILABEL_ERROR+e.getLocalizedMessage();
		final int code = 513;
		return  CustomExceptionUtil.toResponse(code, msg);
	}
}
