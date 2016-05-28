package com.nju.edu.cn.software.test;

import com.nju.edu.cn.software.domain.Author;
import com.nju.edu.cn.software.util.SchoolFriendGson;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Author author = new Author();
		author.setBgUrl("hhhh");
		author.setEmail("1111");
		author.setPhone("123344");
		SchoolFriendGson gson = SchoolFriendGson.newInstance();
		System.out.println(gson.toJson(author));
	}

}
