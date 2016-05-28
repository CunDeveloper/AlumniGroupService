package com.nju.edu.cn.software.domain;

public class AlumnusVoiceComment extends BaseEntity {

	private AuthorInfoShort commentAuthor;
	private AuthorInfoShort commentedAuthor;
	private String content;
	private int alumnusVoiceId;
	
	public AuthorInfoShort getCommentAuthor() {
		return commentAuthor;
	}
	public void setCommentAuthor(AuthorInfoShort commentAuthor) {
		this.commentAuthor = commentAuthor;
	}
	public AuthorInfoShort getCommentedAuthor() {
		return commentedAuthor;
	}
	public void setCommentedAuthor(AuthorInfoShort commentedAuthor) {
		this.commentedAuthor = commentedAuthor;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getAlumnusVoiceId() {
		return alumnusVoiceId;
	}
	public void setAlumnusVoiceId(int alumnusVoiceId) {
		this.alumnusVoiceId = alumnusVoiceId;
	}
	
	
}
