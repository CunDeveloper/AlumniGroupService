package com.nju.edu.cn.software.entity;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class ReqIdEntity {

	@NotNull
	@Min(0)
	private int id;
	 
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	 
	
}
