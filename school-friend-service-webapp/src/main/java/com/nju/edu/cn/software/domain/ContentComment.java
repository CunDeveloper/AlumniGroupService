package com.nju.edu.cn.software.domain;

import javax.validation.constraints.NotNull;

import com.nju.edu.cn.software.entity.AuthorInfo;

public class ContentComment extends BaseEntity{

	private AuthorInfo commentAuthor;
	private AuthorInfoShort commentedAuthor;
	@NotNull
	private String content;
	private int contentId;
	
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getContentId() {
		return contentId;
	}
	public void setContentId(int contentId) {
		this.contentId = contentId;
	}
	 
	public AuthorInfoShort getCommentedAuthor() {
		return commentedAuthor;
	}
	public void setCommentedAuthor(AuthorInfoShort commentedAuthor) {
		this.commentedAuthor = commentedAuthor;
	}
	public AuthorInfo getCommentAuthor() {
		return commentAuthor;
	}
	public void setCommentAuthor(AuthorInfo commentAuthor) {
		this.commentAuthor = commentAuthor;
	}
	
	
	 
	
}
