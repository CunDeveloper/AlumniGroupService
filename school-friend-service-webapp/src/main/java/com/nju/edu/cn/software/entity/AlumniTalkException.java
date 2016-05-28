package com.nju.edu.cn.software.entity;

import java.util.List;

import com.nju.edu.cn.software.domain.RespAlumnicTalk;

public class AlumniTalkException extends RespEntity {

	private List<RespAlumnicTalk> alumnicTalks;

	public List<RespAlumnicTalk> getAlumnicTalks() {
		return alumnicTalks;
	}

	public void setAlumnicTalks(List<RespAlumnicTalk> alumnicTalks) {
		this.alumnicTalks = alumnicTalks;
	}
	
}
