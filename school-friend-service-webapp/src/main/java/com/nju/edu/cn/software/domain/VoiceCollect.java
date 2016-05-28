package com.nju.edu.cn.software.domain;


public class VoiceCollect extends BaseEntity {

	private AlumnusVoice alumnusVoice;
	private int authorId;
	 
	
	public AlumnusVoice getAlumnusVoice() {
		return alumnusVoice;
	}
	public void setAlumnusVoice(AlumnusVoice alumnusVoice) {
		this.alumnusVoice = alumnusVoice;
	}
	public int getAuthorId() {
		return authorId;
	}
	public void setAuthorId(int authorId) {
		this.authorId = authorId;
	}
	 
	
}
