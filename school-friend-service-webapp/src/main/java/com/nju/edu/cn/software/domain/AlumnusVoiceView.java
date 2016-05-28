package com.nju.edu.cn.software.domain;

import java.util.List;

public class AlumnusVoiceView {

	private AlumnusVoice alumnusVoice;
	private List<AlumnusVoiceComment> alumnusVoiceComments;
	private List<AlumnusVoicePraise> alumnusVoicePraises;
	
	public AlumnusVoice getAlumnusVoice() {
		return alumnusVoice;
	}
	public void setAlumnusVoice(AlumnusVoice alumnusVoice) {
		this.alumnusVoice = alumnusVoice;
	}
	public List<AlumnusVoiceComment> getAlumnusVoiceComments() {
		return alumnusVoiceComments;
	}
	public void setAlumnusVoiceComments(
			List<AlumnusVoiceComment> alumnusVoiceComments) {
		this.alumnusVoiceComments = alumnusVoiceComments;
	}
	public List<AlumnusVoicePraise> getAlumnusVoicePraises() {
		return alumnusVoicePraises;
	}
	public void setAlumnusVoicePraises(List<AlumnusVoicePraise> alumnusVoicePraises) {
		this.alumnusVoicePraises = alumnusVoicePraises;
	}
	
	
}
