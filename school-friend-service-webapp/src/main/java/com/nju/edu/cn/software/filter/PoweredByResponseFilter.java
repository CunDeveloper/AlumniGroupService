package com.nju.edu.cn.software.filter;

import java.io.IOException;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.Provider;

import org.apache.log4j.Logger;

import com.nju.edu.cn.software.entity.ExceptionInfo;
import com.nju.edu.cn.software.entity.RespEntity;
import com.nju.edu.cn.software.util.SchoolFriendGson;

@Provider
public class PoweredByResponseFilter implements ContainerResponseFilter {

	private static final String CONTENT_TYPE ="Content-Type";
	private static final String CONTENT_TYPE_VALUE = "text/plain";
	private static final String CONTENT_TYPE_HTML = "text/html;charset=utf-8";
	private static final Logger log = Logger.getLogger(PoweredByResponseFilter.class);
	private static SchoolFriendGson gson = SchoolFriendGson.newInstance();
	@Override
	public void filter(ContainerRequestContext arg0, ContainerResponseContext responseContent) throws IOException {
		// TODO Auto-generated method stub
		MultivaluedMap<String, Object> valueMap =responseContent.getHeaders();
		String contentType = valueMap.get(CONTENT_TYPE).toString();
		if(contentType.contains(CONTENT_TYPE_VALUE)||contentType.contains(CONTENT_TYPE_HTML)){
			String str = responseContent.getEntity().toString();
			int status = responseContent.getStatus();
			RespEntity resp = new RespEntity();
			ExceptionInfo info = new ExceptionInfo();
			info.setCode(status);
			info.setMsg(str);
			resp.setException(info);
			String result = gson.toJson(resp);
			log.info(result);
			responseContent.setEntity(result);
		}
	}

}
