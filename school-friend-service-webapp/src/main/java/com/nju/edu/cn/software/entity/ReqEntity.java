package com.nju.edu.cn.software.entity;

import java.util.List;

public abstract class ReqEntity {

	private int id;
	private LabelInfo label;
	private List<LabelInfo> labelList;


	public LabelInfo getLabel() {
		return label;
	}

	public void setLabel(LabelInfo label) {
		this.label = label;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<LabelInfo> getLabelList() {
		return labelList;
	}

	public void setLabelList(List<LabelInfo> labelList) {
		this.labelList = labelList;
	}
	
}
