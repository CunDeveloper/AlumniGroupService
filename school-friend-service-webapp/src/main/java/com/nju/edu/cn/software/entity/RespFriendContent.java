package com.nju.edu.cn.software.entity;

import com.nju.edu.cn.software.domain.AlumniTalk;

public class RespFriendContent extends RespEntity {
	
	private AlumniTalk alumniTalk;

	public AlumniTalk getFriendContent() {
		return alumniTalk;
	}

	public void setFriendContent(AlumniTalk alumniTalk) {
		this.alumniTalk = alumniTalk;
	}

}
