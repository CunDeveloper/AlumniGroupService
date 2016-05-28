package com.nju.edu.cn.software.domain;

import com.nju.edu.cn.software.entity.AuthorInfo;

public class RespOwnAlumnicTalk {

	private int id;
	private String content;
	private String imagePaths;
	private String date;
	private String location;
	private int whoScan;
	private int praiseCount;
	private int commentCount;
	private AuthorInfo authorInfo;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getImagePaths() {
		return imagePaths;
	}
	public void setImagePaths(String imagePaths) {
		this.imagePaths = imagePaths;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public int getWhoScan() {
		return whoScan;
	}
	public void setWhoScan(int whoScan) {
		this.whoScan = whoScan;
	}
	public AuthorInfo getAuthorInfo() {
		return authorInfo;
	}
	public void setAuthorInfo(AuthorInfo authorInfo) {
		this.authorInfo = authorInfo;
	}
	public int getCommentCount() {
		return commentCount;
	}
	public void setCommentCount(int commentCount) {
		this.commentCount = commentCount;
	}
	public int getPraiseCount() {
		return praiseCount;
	}
	public void setPraiseCount(int praiseCount) {
		this.praiseCount = praiseCount;
	}
	
	
	 
}
