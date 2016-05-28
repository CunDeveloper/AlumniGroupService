package com.nju.edu.cn.software.entity;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class ReqCollectIdEntity {

	@NotNull
	@Min(0)
	private int collectId;

	public int getCollectId() {
		return collectId;
	}

	public void setCollectId(int collectId) {
		this.collectId = collectId;
	}
	
	
}
