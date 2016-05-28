package com.nju.edu.cn.software.domain;

public class AlumniQuestionAnswer extends BaseEntity {

	private String answer;
	private AuthorInfoShort answerAuthor;
	private AuthorInfoShort answeredAuthor;
	private int questionId;
	
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	public AuthorInfoShort getAnswerAuthor() {
		return answerAuthor;
	}
	public void setAnswerAuthor(AuthorInfoShort answerAuthor) {
		this.answerAuthor = answerAuthor;
	}
	public AuthorInfoShort getAnsweredAuthor() {
		return answeredAuthor;
	}
	public void setAnsweredAuthor(AuthorInfoShort answeredAuthor) {
		this.answeredAuthor = answeredAuthor;
	}
	public int getQuestionId() {
		return questionId;
	}
	public void setQuestionId(int questionId) {
		this.questionId = questionId;
	}
}
