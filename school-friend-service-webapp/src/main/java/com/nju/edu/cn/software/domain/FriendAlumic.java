package com.nju.edu.cn.software.domain;

import java.util.List;
/**
 * all of user can view published content
 * @author cun
 *
 */
public class FriendAlumic extends BaseEntity {
 
	private AlumniTalk content;
	private Author praiseAuthor;
	private List<AlumniTalkComment> comments;
	
	public AlumniTalk getContent() {
		return content;
	}
	public void setContent(AlumniTalk content) {
		this.content = content;
	}
	public Author getPraiseAuthor() {
		return praiseAuthor;
	}
	public void setPraiseAuthor(Author praiseAuthor) {
		this.praiseAuthor = praiseAuthor;
	}
	public List<AlumniTalkComment> getComments() {
		return comments;
	}
	public void setComments(List<AlumniTalkComment> comments) {
		this.comments = comments;
	}
	 
	
}
