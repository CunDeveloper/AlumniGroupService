package com.nju.edu.cn.software.util;

import javax.ws.rs.ClientErrorException;
import javax.ws.rs.InternalServerErrorException;
import javax.ws.rs.ServiceUnavailableException;

import org.apache.log4j.Logger;
import org.glassfish.jersey.server.ParamException;

public class ExceptionUtil {

	public static Exception captureException(Exception e){
		 if(e instanceof ClientErrorException){
			 return e;
		 } else if(e instanceof   ParamException.FormParamException){
			 ParamException.FormParamException exception = new ParamException.FormParamException(e,"hello","world");
			 return exception;
		 } else if (e instanceof ServiceUnavailableException){
			 
		 } else if (e instanceof InternalServerErrorException){
			 
		 }
		return e;
	}
	
	public static void recodeException(Exception e,Logger log){
		StackTraceElement[] traceElements = e.getStackTrace();
		for (StackTraceElement traceElement:traceElements){
			log.error(traceElement.getClassName()+"=="+traceElement.getFileName()+"=="
					+traceElement.getLineNumber()+"=="+traceElement.getMethodName()+"=="
					+traceElement.getClass().getSimpleName()+"=="+traceElement.toString());
		}
	}
}
