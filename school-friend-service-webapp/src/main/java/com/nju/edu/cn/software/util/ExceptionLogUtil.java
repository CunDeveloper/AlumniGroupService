package com.nju.edu.cn.software.util;

import java.io.PrintWriter;
import java.io.StringWriter;

import org.apache.log4j.Logger;

public class ExceptionLogUtil {
	private static final String OK_ENTRY_NAME = "request success entry:";
	private static final String OK_LEAVE_NAME = "requset success leave:";
	private static final String ERROR_LEAVE_NAME = "request error leave:";
	
	public static void recordLog(final Logger log,Exception e){
		 StringWriter sw = new StringWriter();
		 e.printStackTrace(new PrintWriter(sw));
		 String exceptionDetails = sw.toString();
		 log.error(exceptionDetails);
	}
	
	public static void entryFunction(final Logger log,final String function){
		log.debug(OK_ENTRY_NAME+function);
	}
	
	public static void successLeaveFunction(final Logger log,final String function){
		log.debug(OK_LEAVE_NAME+function);
	}
	
	public static void errorLeaveFunction(final Logger log,final String function){
		log.error(ERROR_LEAVE_NAME+function);
	}
}
