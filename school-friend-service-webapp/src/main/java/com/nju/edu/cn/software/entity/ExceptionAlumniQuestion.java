package com.nju.edu.cn.software.entity;

import java.util.List;

import com.nju.edu.cn.software.domain.RespAlumniQuestion;

public class ExceptionAlumniQuestion extends RespEntity{

	private List<RespAlumniQuestion> alumniQuestions;

	public List<RespAlumniQuestion> getAlumniQuestions() {
		return alumniQuestions;
	}

	public void setAlumniQuestions(List<RespAlumniQuestion> alumniQuestions) {
		this.alumniQuestions = alumniQuestions;
	}
	
}
