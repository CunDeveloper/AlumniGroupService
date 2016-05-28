package com.nju.edu.cn.software.util;

import javax.inject.Inject;
import javax.ws.rs.container.ContainerRequestContext;

import org.glassfish.hk2.api.Factory;

import com.nju.edu.cn.software.entity.LabelInfo;

public class LabelInfoFactory implements Factory<LabelInfo> {

	private final ContainerRequestContext context;
	
	@Inject
	public LabelInfoFactory(ContainerRequestContext context){
		this.context = context;
	}
	
	@Override
	public void dispose(LabelInfo arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public LabelInfo provide() {
		// TODO Auto-generated method stub
		return (LabelInfo) context.getProperty("label");
	}

}
