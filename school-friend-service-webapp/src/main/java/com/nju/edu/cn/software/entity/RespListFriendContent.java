package com.nju.edu.cn.software.entity;

import java.util.List;

import com.nju.edu.cn.software.domain.AlumniTalk;

public class RespListFriendContent extends RespEntity {
	
	private List<AlumniTalk> alumniTalks;

	public List<AlumniTalk> getFriendContents() {
		return alumniTalks;
	}

	public void setFriendContents(List<AlumniTalk> alumniTalks) {
		this.alumniTalks = alumniTalks;
	}
}
