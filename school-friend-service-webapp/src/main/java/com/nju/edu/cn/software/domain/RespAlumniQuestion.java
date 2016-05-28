package com.nju.edu.cn.software.domain;

import com.nju.edu.cn.software.entity.AuthorInfo;

public class RespAlumniQuestion extends BaseEntity{

	private String problem;
	private String description;
	private String imgPaths;
	private AuthorInfo author;
	private int replayCount;
	private String label;
	
 
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
	public void setImgPaths(String imgPaths) {
		this.imgPaths = imgPaths;
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
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	 
}
