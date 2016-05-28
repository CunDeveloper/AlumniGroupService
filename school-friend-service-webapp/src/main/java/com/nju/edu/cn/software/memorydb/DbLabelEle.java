package com.nju.edu.cn.software.memorydb;

import com.nju.edu.cn.software.entity.LabelInfo;

public class DbLabelEle extends LabelInfo {
	private int authorId;//author id
	private int visitCount;//record visit count;
	
	public int getAuthorId() {
		return authorId;
	}
	public void setAuthorId(int authorId) {
		this.authorId = authorId;
	}
	public int getVisitCount() {
		return visitCount;
	}
	public void setVisitCount(int visitCount) {
		this.visitCount = visitCount;
	}
	
	
}
