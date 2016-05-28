package com.nju.edu.cn.software.domain;

public class RecommendCollect extends BaseEntity {

	private RecommendWork recommendWork;
	private int authorId;
	public RecommendWork getRecommendWork() {
		return recommendWork;
	}
	public void setRecommendWork(RecommendWork recommendWork) {
		this.recommendWork = recommendWork;
	}
	public int getAuthorId() {
		return authorId;
	}
	public void setAuthorId(int authorId) {
		this.authorId = authorId;
	}
	
	
}
