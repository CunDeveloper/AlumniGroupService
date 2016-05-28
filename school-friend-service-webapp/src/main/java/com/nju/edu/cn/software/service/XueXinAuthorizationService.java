package com.nju.edu.cn.software.service;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;
import org.glassfish.jersey.media.multipart.FormDataMultiPart;

import com.nju.edu.cn.software.helper.Constant;
import com.nju.edu.cn.software.oauth.Authorization;
import com.nju.edu.cn.software.oauth.XueXinService;

@Path("auth")
public class XueXinAuthorizationService {

	private static final Logger log = Logger.getLogger(XueXinAuthorizationService.class);
	
	private static final String USERNAME = "username";
	private static final String PASSWORD = "password";
	private static final String CAPTCHA = "captcha";
	private static final String AUTH_OBJECT = "authorization";
	
	@GET
	@Path("xueXin")
	@Produces(MediaType.TEXT_PLAIN)
	public String getTest(@Context HttpServletRequest request,FormDataMultiPart multiPart){
		
		Authorization authorization = null;
		if((authorization=(Authorization) request.getSession().getAttribute(AUTH_OBJECT)) == null) {
			 authorization = new Authorization();
			 request.getSession().setAttribute(AUTH_OBJECT,authorization);
		}
		final String captcha = null;
		final String username = null;
		final String password = null;
		final String label_id = null;
		XueXinService service = new XueXinService(authorization);
		if(captcha ==null || captcha.equals("")){ 
			String result = null;
			try {
				result = service.login(username, password,label_id);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(result.equals(Constant.HTTP_ERROR) || result.equals(Constant.HTTP_URL_ERROR)) {
//				out.write(result.getBytes(UTF_8));
			} else{
//				loginExe(authorization,result,request,response,out);
			}
			
		}
		String test = null;
//		else{
// 			String result = service.loginWithCaptcha(request.getSession().getAttribute(Constant.XUE_XIN_IT).toString(), username, password, captcha, label_id);
//			if(result.equals(Constant.HTTP_ERROR) || result.equals(Constant.HTTP_URL_ERROR)) {
//				//out.print(result);
//				out.write(result.getBytes(UTF_8));
//			} else{
//				loginExe(authorization,result,request,response,out);
//			}
//		}
//		out.close();
//		
//		String test="";
//		try{
//			log.info("sheng mie qingk"+httpRequest.getRemoteAddr());
//			  Object object= httpRequest.getSession().getAttribute("hello");
//			  if(object==null){
//				  log.info("dear jia xia juan");
//			  }
//			  else{
//				  test = object.toString();
//			  }
//			  log.info("shirt"+test);
//			if(test==null||test.equals("")){
//				test = "nima";
//				httpRequest.getSession().setAttribute("hello","zhangxiaojun");
//			}
//			
//			
//		}catch(Exception e){
//			e.printStackTrace();
//		}
		return test;
	}
	
//	private void loginExe(Authorization authorization,String result,HttpServletRequest request,HttpServletResponse response, OutputStream out) throws IOException {
//		Map<Object, Object> resultMap =gson.fromJsonToMap(result);
//	 
//		if(resultMap.containsKey(Constant.XUE_XIN_CAPTCHA)) {
//			String IT = authorization.getItT(resultMap.get(Constant.XUE_XIN_CAPTCHA).toString());
//			request.getSession().setAttribute(Constant.XUE_XIN_IT,IT);
//			 
//			byte[] buffer =authorization.getCaptcha();
//			 
//			int length = buffer.length;
//			byte[] finBuffer = new byte[length+1];
//			System.arraycopy(buffer, 0, finBuffer, 0, length);
//			finBuffer[length]='#';
//			out.write(finBuffer);
//		} else {
//			out.write(result.getBytes(UTF_8));
//		}
//	}
}
