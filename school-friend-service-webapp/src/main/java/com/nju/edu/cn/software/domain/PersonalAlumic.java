package com.nju.edu.cn.software.domain;

import java.util.List;
/**
 *  
 * @author cun
 *
 */
public class PersonalAlumic extends BaseEntity {
 
	private AlumniTalk content;
	private List<Author> praiseAuthors;
	private List<AlumniTalkComment> comments;
	public AlumniTalk getContent() {
		return content;
	}
	public void setContent(AlumniTalk content) {
		this.content = content;
	}
	public List<Author> getPraiseAuthors() {
		return praiseAuthors;
	}
	public void setPraiseAuthors(List<Author> praiseAuthors) {
		this.praiseAuthors = praiseAuthors;
	}
	public List<AlumniTalkComment> getComments() {
		return comments;
	}
	public void setComments(List<AlumniTalkComment> comments) {
		this.comments = comments;
	}
	 
	
	
}
