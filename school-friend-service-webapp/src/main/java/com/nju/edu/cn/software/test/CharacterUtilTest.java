package com.nju.edu.cn.software.test;

import org.junit.Test;

import com.nju.edu.cn.software.util.CharacterUtil;

public class CharacterUtilTest {

	@Test
	public void testGenerateRandomStr() {
		System.out.println(CharacterUtil.generateRandomStr(32));
	}

}
