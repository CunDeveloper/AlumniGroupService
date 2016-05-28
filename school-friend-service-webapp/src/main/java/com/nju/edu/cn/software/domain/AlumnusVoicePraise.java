package com.nju.edu.cn.software.domain;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.nju.edu.cn.software.entity.AuthorInfo;

public class AlumnusVoicePraise extends BaseEntity {

	private AuthorInfo praiseAuthor;
	@NotNull
	@Min(0)
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
