package com.nju.edu.cn.software.service;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotNull;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.nju.edu.cn.software.helper.Constant;
import com.nju.edu.cn.software.helper.PathConstant;
import com.nju.edu.cn.software.mapper.AuthorMapper;
import com.nju.edu.cn.software.util.CharacterUtil;
import com.nju.edu.cn.software.util.CustomExceptionUtil;
import com.nju.edu.cn.software.util.ExceptionUtil;

@Path(PathConstant.REGISTER_PATH)
@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
@Produces(MediaType.APPLICATION_JSON)
public class RegisterService {

	private @Context HttpServletRequest request;
	private static final String CAPTCHA = "captcha";
	@Autowired
	private AuthorMapper authorDao;
	private static final Logger log = Logger.getLogger(RegisterService.class);
	
	@POST
	@Path(PathConstant.REGISTER_SUB_PATH_PHONE)
	public Response phoneRegister(@NotNull @FormParam("phone")String phone){
		try{
			if(authorDao.validatePhoneIsRegister(phone)>0){
				return CustomExceptionUtil.toResponse(Constant.CODE_202,"phone has been registered");
			}else{
				String captcha = CharacterUtil.generateValidateCode(5);
				request.getSession().setAttribute(CAPTCHA,captcha);
				return CustomExceptionUtil.toResponse(captcha);
			}
		}catch(Exception e){
			ExceptionUtil.recodeException(e, log);
			throw e;
		}
	}
	
	@POST
	@Path(PathConstant.REGISTER_SUB_PATH_PHONE_VALIDATE)
	public Response phoneValidate(@NotNull(message="catpcha 不能为空") @FormParam("captcha") String captcha){
		try{
			String sCaptcha = (String) request.getSession().getAttribute(CAPTCHA);
			if(sCaptcha==null){
				return CustomExceptionUtil.toResponse(Constant.CODE_202,"验证码已经失效");
			}else{
				if(sCaptcha.equals(captcha)){
					return CustomExceptionUtil.toResponse(Constant.OK_MSG);
				}else{
					return CustomExceptionUtil.toResponse(Constant.CODE_202,"验证码错误");
				}
			}
		}catch(Exception e){
			ExceptionUtil.recodeException(e, log);
			throw e;
		}	
	}
}
