package com.nju.edu.cn.software.util;

import org.glassfish.jersey.media.multipart.FormDataMultiPart;
import com.nju.edu.cn.software.domain.AuthenticationAccessToken;
import com.nju.edu.cn.software.helper.Constant;
import com.nju.edu.cn.software.mapper.AuthorizationTokenMapper;

public class FormDataToken {
	 
	public int tokenValidate(FormDataMultiPart multiPart,AuthorizationTokenMapper authTokenDao){
		int authorId = 0;
		String authorization = multiPart.getFields().get(Constant.AUTHORIZATION).get(0).getValue();//authorization validate
		AuthenticationAccessToken  accessToken = TokenUtil.getAccessToken(authorization);
		if(accessToken !=null){
			  int id = authTokenDao.validateAuthorizationToken(accessToken.getTokenId(),
					accessToken.getUserId(),accessToken.getDiviceId(), accessToken.getAppId());
			  if(1==id){
				  authorId = accessToken.getUserId();
			  }
		} 
		return authorId;
	}
}
