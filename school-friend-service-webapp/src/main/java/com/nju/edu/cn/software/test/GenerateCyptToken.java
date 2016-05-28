package com.nju.edu.cn.software.test;

import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;

import com.nju.edu.cn.software.util.CyptUtil;

public class GenerateCyptToken {

	private static final String FILE_NAME = "tokenTest.txt";
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		InputStream in= GenerateCyptToken.class.getClassLoader().getResourceAsStream(FILE_NAME);
		try {
			final String fileStr = IOUtils.toString(in, "UTF-8");
			System.out.println(CyptUtil.getEncryptiedData(fileStr));
		   System.out.println();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
