package com.nju.edu.cn.software.domain;

import com.nju.edu.cn.software.entity.AuthorInfo;

public class AlumnicTalkPraise extends BaseEntity{

	private AuthorInfo praiseAuthor;
	private int contentId;
	 
	 
	public int getContentId() {
		return contentId;
	}
	public void setContentId(int contentId) {
		this.contentId = contentId;
	}
	public AuthorInfo getPraiseAuthor() {
		return praiseAuthor;
	}
	public void setPraiseAuthor(AuthorInfo praiseAuthor) {
		this.praiseAuthor = praiseAuthor;
	}
 
	
}
