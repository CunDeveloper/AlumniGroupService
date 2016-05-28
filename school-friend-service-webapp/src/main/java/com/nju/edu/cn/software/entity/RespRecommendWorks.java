package com.nju.edu.cn.software.entity;

import java.util.List;

import com.nju.edu.cn.software.domain.RecommendWork;
import com.nju.edu.cn.software.domain.RespRecommendWork;

public class RespRecommendWorks extends RespEntity {

	private List<RespRecommendWork> recommendWorks;
 
	public List<RespRecommendWork> getRecommendWorks() {
		return recommendWorks;
	}

	public void setRecommendWorks(List<RespRecommendWork> recommendWorks) {
		this.recommendWorks = recommendWorks;
	}
	
}
