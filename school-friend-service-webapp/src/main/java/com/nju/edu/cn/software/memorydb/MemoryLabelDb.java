package com.nju.edu.cn.software.memorydb;

import java.util.HashMap;
import java.util.Map;

public class MemoryLabelDb {

	private static final Map<Integer,DbLabelEle> db = new HashMap<>();
	
	public DbLabelEle query(Integer author_id){
		return db.get(author_id);
	}
}
