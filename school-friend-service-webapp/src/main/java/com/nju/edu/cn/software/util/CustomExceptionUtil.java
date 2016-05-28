package com.nju.edu.cn.software.util;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.nju.edu.cn.software.entity.ExceptionInfo;
import com.nju.edu.cn.software.entity.RespEntity;
import com.nju.edu.cn.software.helper.Constant;

public class CustomExceptionUtil {

	public static Response toResponse(final int code,final String msg){
		RespEntity<String> resp = new RespEntity<>();
		resp.setInfo(msg);
		ExceptionInfo info = new ExceptionInfo();
		info.setCode(code);
		info.setMsg(msg);
		resp.setException(info);
		return Response.ok().entity(resp).type(MediaType.APPLICATION_JSON).build();
	}
	
	public static <T> Response toResponse(T t){
		RespEntity<T> resp = new RespEntity<>();
		resp.setInfo(t);
		ExceptionInfo info = new ExceptionInfo();
		info.setCode(Constant.OK_CODE);
		info.setMsg(Constant.OK_MSG);
		 
		resp.setException(info);
		return Response.ok().entity(resp).type(MediaType.APPLICATION_JSON).build();
	}
	
	public static Response toResponse(){
		RespEntity<String> resp = new RespEntity<String>();
		resp.setInfo(Constant.OK_MSG);
		ExceptionInfo info = new ExceptionInfo();
		info.setCode(Constant.OK_CODE);
		info.setMsg(Constant.OK_MSG);
		resp.setException(info);
		return Response.ok().entity(resp).type(MediaType.APPLICATION_JSON).build();
	}
}
