package com.nju.edu.cn.software.domain;

import com.nju.edu.cn.software.entity.AuthorInfo;

public class RespAlumnicVoice extends BaseEntity {

	private String title;
	private String content;
	private String imgPaths;
	private int praiseCount;
	private int commentCount;
	private AuthorInfo author;
	private String label;
	 
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getImgPaths() {
		return imgPaths;
	}
	public void setImgPaths(String imgPaths) {
		this.imgPaths = imgPaths;
	}
	public AuthorInfo getAuthor() {
		return author;
	}
	public void setAuthor(AuthorInfo author) {
		this.author = author;
	}
 
	public int getPraiseCount() {
		return praiseCount;
	}
	public void setPraiseCount(int praiseCount) {
		this.praiseCount = praiseCount;
	}
	public int getCommentCount() {
		return commentCount;
	}
	public void setCommentCount(int commentCount) {
		this.commentCount = commentCount;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	 
	
}
