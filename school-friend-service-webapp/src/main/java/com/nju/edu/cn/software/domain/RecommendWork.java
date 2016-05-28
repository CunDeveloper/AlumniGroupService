package com.nju.edu.cn.software.domain;

import java.util.ArrayList;
import java.util.List;

import com.nju.edu.cn.software.entity.AuthorInfo;

public class RecommendWork extends BaseEntity {

	private AuthorInfo author;
	private String title;
	private String content;
	private String imgPaths;
	private int commentCount;
	private int type;
	private String email;
	
	private List<String> images = new ArrayList<>();
	
	 
	public String getContent() {
		return content;
	}
	
	public void setImages(List<String> images) {
		this.images = images;
		setImgPaths();
	}
	
	public void setContent(String content) {
		this.content = content;
	}
	 
	public String getImgPaths() {
		return imgPaths;
	}
	
	public void setImgPaths() {
		StringBuilder builder = new StringBuilder();
		for(String path:images){
			builder.append(path);
			builder.append(",");
		}
		if(builder.length()>0){
			builder.deleteCharAt(builder.length()-1);
		}
		 this.imgPaths = builder.toString();
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}

	public AuthorInfo getAuthor() {
		return author;
	}

	public void setAuthor(AuthorInfo author) {
		this.author = author;
	}

	public int getCommentCount() {
		return commentCount;
	}

	public void setCommentCount(int commentCount) {
		this.commentCount = commentCount;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	
}
