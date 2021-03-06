package com.nju.edu.cn.software.exception;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.nju.edu.cn.software.api.ErrorResponse;

public abstract class BaseWebApplicationException extends WebApplicationException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final int status;
	private final String errorMessage;
	private final String developerMessage;
	
	public BaseWebApplicationException(int httpStatus,String errorMessage,String developerMessage){
		this.status = httpStatus;
		this.errorMessage = errorMessage;
		this.developerMessage = developerMessage;
	}
	
	@Override
	public Response getResponse(){
		return Response.status(status).type(MediaType.APPLICATION_JSON).entity(getResponse()).build();
	}
	
	@Override
	public String getMessage(){
		return errorMessage;
	}
	
	public ErrorResponse getErrorResponse(){
		ErrorResponse response = new ErrorResponse();
		response.setApplicationMessage(developerMessage);
		response.setConsumerMessage(errorMessage);
		return response;
	}

}
