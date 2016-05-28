package com.nju.edu.cn.software.resource;

import com.nju.edu.cn.software.domain.Author;

public class RespAuthor {

	private StatusInfo statusInfo;
	private Author author;
	public StatusInfo getStatusInfo() {
		return statusInfo;
	}
	public void setStatusInfo(StatusInfo statusInfo) {
		this.statusInfo = statusInfo;
	}
	public Author getAuthor() {
		return author;
	}
	public void setAuthor(Author author) {
		this.author = author;
	}
	
}
