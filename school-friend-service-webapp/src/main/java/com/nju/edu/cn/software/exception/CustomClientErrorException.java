package com.nju.edu.cn.software.exception;

import javax.ws.rs.ClientErrorException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.nju.edu.cn.software.helper.Constant;
import com.nju.edu.cn.software.util.CustomExceptionUtil;

@Provider
public class CustomClientErrorException implements ExceptionMapper<ClientErrorException> {
	@Override
	public Response toResponse(ClientErrorException e) {
 
		final String msg = Constant.CLIENT_ERROR+e.getLocalizedMessage();
		final int code = 412;
		return  CustomExceptionUtil.toResponse(code, msg);
	}
}
