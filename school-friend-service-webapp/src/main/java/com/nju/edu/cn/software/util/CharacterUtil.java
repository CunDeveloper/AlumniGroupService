package com.nju.edu.cn.software.util;

import java.util.Random;

public class CharacterUtil {

	private final static char[] chars = {
		'a','s','d','f','g','h','j','k','l',
		'q','w','e','r','t','y','u','i','o','p',
		'1','2','3','4','5','6','7','8','9','0',
		'z','x','c','v','b','n','n','m','Q','W',
		'E','R','T','Y','U','I','O','P','A','S','D'
		,'F','G','H','J','K','L','Z','X','C','V',
		'B','N','M'
	};
	
	private final static int[] numbers={
		1,2,3,4,5,6,7,8,9,0
	};
	
	private static final int LENGTH = chars.length;
	
	public static String generateRandomStr(int size) {
		Random random = new Random();
		StringBuilder builder = new StringBuilder();
		for(int i = 0;i<size;i++){
			builder.append(chars[random.nextInt(LENGTH)]);
		}
		return builder.toString();
	}
	
	public static String generateValidateCode(int size) {
		Random random = new Random();
		StringBuilder builder = new StringBuilder();
		for(int i = 0;i<size;i++){
			builder.append(numbers[random.nextInt(numbers.length)]);
		}
		return builder.toString();
	}
}
