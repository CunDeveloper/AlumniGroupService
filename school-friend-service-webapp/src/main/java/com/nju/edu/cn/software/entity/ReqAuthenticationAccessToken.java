package com.nju.edu.cn.software.entity;

import com.nju.edu.cn.software.domain.AuthenticationAccessToken;

public class ReqAuthenticationAccessToken extends ReqEntity {

	private AuthenticationAccessToken authenticationAccessToken;

	public AuthenticationAccessToken getAuthenticationAccessToken() {
		return authenticationAccessToken;
	}

	public void setAuthenticationAccessToken(AuthenticationAccessToken authenticationAccessToken) {
		this.authenticationAccessToken = authenticationAccessToken;
	}
}
