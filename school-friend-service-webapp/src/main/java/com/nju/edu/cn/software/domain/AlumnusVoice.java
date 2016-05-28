package com.nju.edu.cn.software.domain;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.nju.edu.cn.software.entity.AuthorInfo;

public class AlumnusVoice extends BaseEntity {

	private String title;
	@NotNull
	private String content;
	private String imgPaths;
	private int whoScan;
	@NotNull
	private String  label  ;
	
	private AuthorInfo author;
	
	@JsonIgnore
	private List<String> images = new ArrayList<>();
	
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
	public int getWhoScan() {
		return whoScan;
	}
	public void setWhoScan(int whoScan) {
		this.whoScan = whoScan;
	}
	public AuthorInfo getAuthor() {
		return author;
	}
	public void setAuthor(AuthorInfo author) {
		this.author = author;
	}
	 
	 
	public void setImages(List<String> images) {
		this.images = images;
		setImgPaths();
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public List<String> getImages() {
		return images;
	}
	public void setImgPaths(String imgPaths) {
		this.imgPaths = imgPaths;
	}
 
	 
}
