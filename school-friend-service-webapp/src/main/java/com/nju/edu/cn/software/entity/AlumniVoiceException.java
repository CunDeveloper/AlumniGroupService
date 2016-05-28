package com.nju.edu.cn.software.entity;

import java.util.List;

import com.nju.edu.cn.software.domain.RespAlumnicVoice;

public class AlumniVoiceException extends RespEntity{

	private List<RespAlumnicVoice> alumnicVoices;

	public List<RespAlumnicVoice> getAlumnicVoices() {
		return alumnicVoices;
	}

	public void setAlumnicVoices(List<RespAlumnicVoice> alumnicVoices) {
		this.alumnicVoices = alumnicVoices;
	}
}
