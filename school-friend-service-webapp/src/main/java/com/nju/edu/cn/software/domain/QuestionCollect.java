package com.nju.edu.cn.software.domain;

public class QuestionCollect extends BaseEntity {

	private AlumniQuestion alumniQuestion;
	private int authorId;
	public AlumniQuestion getAlumniQuestion() {
		return alumniQuestion;
	}
	public void setAlumniQuestion(AlumniQuestion alumniQuestion) {
		this.alumniQuestion = alumniQuestion;
	}
	public int getAuthorId() {
		return authorId;
	}
	public void setAuthorId(int authorId) {
		this.authorId = authorId;
	}
	
	
}
