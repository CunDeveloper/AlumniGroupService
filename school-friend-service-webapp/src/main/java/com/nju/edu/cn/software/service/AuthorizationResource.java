package com.nju.edu.cn.software.service;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

 

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.nju.edu.cn.software.domain.AuthenticationAccessToken;
import com.nju.edu.cn.software.helper.PathConstant;
import com.nju.edu.cn.software.mapper.AuthorizationTokenMapper;
import com.nju.edu.cn.software.util.AppUtil;
import com.nju.edu.cn.software.util.CustomExceptionUtil;
import com.nju.edu.cn.software.util.CyptUtil;
import com.nju.edu.cn.software.util.ExceptionUtil;
import com.nju.edu.cn.software.util.TokenUtil;

@Path(PathConstant.AUTHORIZATION_PATH)
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AuthorizationResource {

	private static final Logger log = Logger.getLogger(AuthorizationResource.class);
	
	@Autowired
	private AuthorizationTokenMapper tokenDao;
	
	@POST
	@Path(PathConstant.AUTHORIZATION_SUB_PATH_SAVE)
	public Response saveToken(AuthenticationAccessToken token){
		try{
			token.setTokenId(TokenUtil.getTokenId());
			token.setAppId(AppUtil.getAppId());
			tokenDao.saveAuthorizationToken(token);
			return CustomExceptionUtil.toResponse(CyptUtil.tokenEncrypt(token.toString()));
		}catch(Exception e){
			ExceptionUtil.recodeException(e, log);
			throw e;
		}
	}
	
	@POST
	@Path(PathConstant.AUTHORIZATION_SUB_PATH_UPDATE)
	public Response updateToken(AuthenticationAccessToken token){
		token.setTokenId(TokenUtil.getTokenId());
		try{
			tokenDao.updateToken(token.getTokenId(),token.getDiviceId(),token.getUserId());
			return CustomExceptionUtil.toResponse(CyptUtil.tokenEncrypt(token.toString()));
		}catch(Exception e){
			ExceptionUtil.recodeException(e, log);
			throw e;
		}
	}
}
