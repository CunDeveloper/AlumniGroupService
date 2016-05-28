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
import com.nju.edu.cn.software.domain.XueXinAuthorization;
import com.nju.edu.cn.software.helper.PathConstant;
import com.nju.edu.cn.software.mapper.AuthorizationTokenMapper;
import com.nju.edu.cn.software.util.CustomExceptionUtil;
import com.nju.edu.cn.software.util.SchoolFriendGson;
import com.nju.edu.cn.software.util.TokenUtil;

@Path(PathConstant.AUTHORIZATION_PATH)
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AuthorizationService {

	private static SchoolFriendGson gson = SchoolFriendGson.newInstance();
	private static final Logger log = Logger.getLogger(AuthorizationService.class);
	@Autowired
	private AuthorizationTokenMapper authorizationTokenDao;
	
//	@POST
//	@Path(PathConstant.AUTHORIZATION_SUB_PATH_XUE_XIN_NET)
//	public Response authXueXinNet(XueXinAuthorization xueXinAuth){
//		AuthenticationAccessToken accessToken = new AuthenticationAccessToken();
//		accessToken.setTokenId(TokenUtil.getTokenId());
//		accessToken.setAppId(xueXinAuth.getAppId());
//		accessToken.setDiviceId(xueXinAuth.getDeviceId());
//		authorizationTokenDao.saveAuthorizationToken(accessToken);
//		return CustomExceptionUtil.toResponse(gson.toJson(accessToken));
//	}
}
