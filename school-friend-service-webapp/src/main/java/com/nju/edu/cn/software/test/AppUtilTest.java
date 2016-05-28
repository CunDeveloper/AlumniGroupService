package com.nju.edu.cn.software.test;

import org.junit.Test;

import com.nju.edu.cn.software.util.AppUtil;

public class AppUtilTest {

	@Test
	public void testGetAppId() {
		System.out.println(AppUtil.getAppId());
	}
	
	@Test
	public void testUpdateAppId(){
		AppUtil.updateAppId("dsfsdfs33hsdfe434llowosdfrld");
	}

}
