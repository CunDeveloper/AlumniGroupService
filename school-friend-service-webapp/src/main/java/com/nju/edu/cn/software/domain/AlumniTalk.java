package com.nju.edu.cn.software.domain;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.nju.edu.cn.software.entity.AuthorInfo;

public class AlumniTalk extends BaseEntity {
	private String content;
	private AuthorInfo author;
	private String imagePaths;
	private int startYear;
	private String college;
	private int whoScan;
	private String location;
	
	 @JsonIgnore
	private List<String> images = new ArrayList<>();
	
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public void setImages(List<String> images) {
		this.images = images;
		setImagePaths();
	}
	public String getImagePaths() {
		return imagePaths;
	}
	public void setImagePaths() {
		StringBuilder builder = new StringBuilder();
		for(String path:images){
			builder.append(path);
			builder.append(",");
		}
		if(builder.length()>0){
			builder.deleteCharAt(builder.length()-1);
		}
		this.imagePaths = builder.toString();
	}
	public AuthorInfo getAuthor() {
		return author;
	}
	public void setAuthor(AuthorInfo author) {
		this.author = author;
	}
	 
	public String getCollege() {
		return college;
	}
	public void setCollege(String college) {
		this.college = college;
	}
	public int getWhoScan() {
		return whoScan;
	}
	public void setWhoScan(int whoScan) {
		this.whoScan = whoScan;
	}
	public int getStartYear() {
		return startYear;
	}
	public void setStartYear(int startYear) {
		this.startYear = startYear;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	 
	
	
}
