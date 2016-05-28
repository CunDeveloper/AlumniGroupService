package com.nju.edu.cn.software.domain;

import com.nju.edu.cn.software.entity.AuthorInfo;

public class AlumniTalkComment extends BaseEntity{

	private AuthorInfo commentAuthor;
	private AuthorInfo commentedAuthor;
	private String content;
	private int alumnicTalkId;
	 
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getAlumnicTalkId() {
		return alumnicTalkId;
	}
	public void setAlumnicTalkId(int alumnicTalkId) {
		this.alumnicTalkId = alumnicTalkId;
	}
	 
	public AuthorInfo getCommentAuthor() {
		return commentAuthor;
	}
	public void setCommentAuthor(AuthorInfo commentAuthor) {
		this.commentAuthor = commentAuthor;
	}
	public AuthorInfo getCommentedAuthor() {
		return commentedAuthor;
	}
	public void setCommentedAuthor(AuthorInfo commentedAuthor) {
		this.commentedAuthor = commentedAuthor;
	}
	  
}
