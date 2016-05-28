package com.nju.edu.cn.software.exception;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.nju.edu.cn.software.api.ErrorResponse;
import com.nju.edu.cn.software.api.ValidationError;

public class ValidationException extends WebApplicationException { 
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final int status = 400;
	private String errorMessage;
	private String developerMessage;
	private List<ValidationError> errors = new ArrayList<ValidationError>();
	
	public ValidationException(){
		errorMessage = "Validation Error";
		developerMessage = "The data passed in the request was invalid.Please check and resubmit";
	}
	
	public ValidationException(String message){
		super();
		errorMessage = message;
	}
	
	public ValidationException(Set<? extends ConstraintViolation<?>> violations){
		this();
		for(ConstraintViolation<?> constraintVoilation:violations){
			ValidationError error = new ValidationError();
			error.setMessage(constraintVoilation.getMessage());
			error.setPropertyName(constraintVoilation.getPropertyPath().toString());
			error.setPropertyValue(constraintVoilation.getInvalidValue() != null ? constraintVoilation.getInvalidValue().toString() : null);
			errors.add(error);
			
		}
	}
	
	@Override
	public Response getResponse(){
		return Response.status(status).type(MediaType.APPLICATION_JSON).entity(getErrorResponse()).build();
	}
	
	public ErrorResponse getErrorResponse(){
		ErrorResponse response = new ErrorResponse();
		response.setApplicationMessage(developerMessage);
		response.setConsumerMessage(errorMessage);
		response.setValidationErrors(errors);
		return response;
	}
}
