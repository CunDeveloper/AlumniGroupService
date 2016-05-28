package com.nju.edu.cn.software.mapper;

import org.apache.ibatis.annotations.Param;

import com.nju.edu.cn.software.domain.AuthenticationAccessToken;

public interface AuthorizationTokenMapper {

	void saveAuthorizationToken(AuthenticationAccessToken  accessToken);
	void updateAuthorizationToken(@Param("tokenId")String tokenId,@Param("userId")int userId);
	void updateToken(@Param("tokenId")String tokenId,@Param("diviceId") String diviceId,@Param("userId") int userId);
	//if return the only a username ,represent validate success
	int  validateAuthorizationToken(@Param("tokenId")String tokenId,@Param("userId")int userId,@Param("diviceId")String diviceId,@Param("appId") String appId);
	AuthenticationAccessToken getAuthorizationToken(@Param("userId")int userId);
	
}
