package com.nju.edu.cn.software.domain;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.nju.edu.cn.software.entity.AuthorInfo;

public class AlumniQuestion extends BaseEntity {
 
	private String problem;
	private String description;
	private String imgPaths;
	private AuthorInfo author;
	private int replayCount;
	private boolean isSolved;
	private int whoScan;
	private String label;
	
	@JsonIgnore
	private List<String> images = new ArrayList<>();
	
	public String getProblem() {
		return problem;
	}
	public void setProblem(String problem) {
		this.problem = problem;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
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
	public AuthorInfo getAuthor() {
		return author;
	}
	public void setAuthor(AuthorInfo author) {
		this.author = author;
	}
	public int getReplayCount() {
		return replayCount;
	}
	public void setReplayCount(int replayCount) {
		this.replayCount = replayCount;
	}
	public boolean isSolved() {
		return isSolved;
	}
	public void setSolved(boolean isSolved) {
		this.isSolved = isSolved;
	}
	public int getWhoScan() {
		return whoScan;
	}
	public void setWhoScan(int whoScan) {
		this.whoScan = whoScan;
	}
	public List<String> getImages() {
		return images;
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
	
	
}
