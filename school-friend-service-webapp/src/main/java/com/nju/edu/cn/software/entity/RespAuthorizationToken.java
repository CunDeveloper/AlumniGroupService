package com.nju.edu.cn.software.entity;

import com.nju.edu.cn.software.domain.AuthenticationAccessToken;

public class RespAuthorizationToken extends RespEntity {

	private AuthenticationAccessToken accessToken;

	public AuthenticationAccessToken getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(AuthenticationAccessToken accessToken) {
		this.accessToken = accessToken;
	}
}
