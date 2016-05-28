package com.nju.edu.cn.software.domain;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class AuthenticationAccessToken {

	@NotNull
	private String tokenId;
	@NotNull
	@Min(0)
	private int userId;
	private String userName;
	@NotNull
	private String diviceId;
	@NotNull
	private String appId;
	
	public String getTokenId() {
		return tokenId;
	}
	public void setTokenId(String tokenId) {
		this.tokenId = tokenId;
	}
	 
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getDiviceId() {
		return diviceId;
	}
	public void setDiviceId(String diviceId) {
		this.diviceId = diviceId;
	}
	public String getAppId() {
		return appId;
	}
	public void setAppId(String appId) {
		this.appId = appId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	 
	 
	
}
