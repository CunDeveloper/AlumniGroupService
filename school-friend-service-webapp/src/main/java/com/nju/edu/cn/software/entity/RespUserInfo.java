package com.nju.edu.cn.software.entity;

import com.nju.edu.cn.software.domain.UserInfo;

public class RespUserInfo extends RespEntity {

	private UserInfo userInfo;
	 
	public UserInfo getUserInfo() {
		return userInfo;
	}
	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}
 
	
}
