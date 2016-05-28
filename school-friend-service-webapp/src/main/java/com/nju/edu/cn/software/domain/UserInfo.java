package com.nju.edu.cn.software.domain;

import java.util.List;

public class UserInfo {

	private Author authorInfo;
	private List<UserDegreeInfo> degreeInfos;
	
	public Author getAuthorInfo() {
		return authorInfo;
	}
	public void setAuthorInfo(Author authorInfo) {
		this.authorInfo = authorInfo;
	}
	public List<UserDegreeInfo> getDegreeInfos() {
		return degreeInfos;
	}
	public void setDegreeInfos(List<UserDegreeInfo> degreeInfos) {
		this.degreeInfos = degreeInfos;
	}
	
	
}
