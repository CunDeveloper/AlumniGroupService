package com.nju.edu.cn.software.exception;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

import org.springframework.security.access.AccessDeniedException;

import com.nju.edu.cn.software.api.ErrorResponse;
import com.nju.edu.cn.software.helper.Constant;

public class AccessDeniedExceptionMapper implements ExceptionMapper<AccessDeniedException> {

	@Override
	public Response toResponse(AccessDeniedException exception) {
		// TODO Auto-generated method stub
		return Response.status(Response.Status.UNAUTHORIZED)
				.entity(new ErrorResponse(Constant.UNAUTHORIZED_CODE+"",Constant.UNAUTHORIZED_MEG,exception.getMessage()))
				.type(MediaType.APPLICATION_JSON)
				.build();
	}

}
