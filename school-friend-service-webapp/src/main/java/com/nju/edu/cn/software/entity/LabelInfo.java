package com.nju.edu.cn.software.entity;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class LabelInfo {

	@NotNull
	private String university;
	@NotNull
	private String college;
	@NotNull
	@Min(1950)
	private int entryYear;
	@NotNull
	private String level;
	@NotNull
	@Min(0)
	private int authorId;
	private String tokenId;
	
	public String getUniversity() {
		return university;
	}
	public void setUniversity(String university) {
		this.university = university;
	}
	public String getCollege() {
		return college;
	}
	public void setCollege(String college) {
		this.college = college;
	}
	 
	public int getAuthorId() {
		return authorId;
	}
	public void setAuthorId(int authorId) {
		this.authorId = authorId;
	}
	 
	public String getTokenId() {
		return tokenId;
	}
	public void setTokenId(String tokenId) {
		this.tokenId = tokenId;
	}
	public int getEntryYear() {
		return entryYear;
	}
	public void setEntryYear(int entryYear) {
		this.entryYear = entryYear;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	
	
	
}
