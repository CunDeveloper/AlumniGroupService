package com.nju.edu.cn.software.util;

import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.UriInfo;

import com.nju.edu.cn.software.entity.LabelInfo;

public class PathLabel {

	private static final String UNIVERSITY = "university";
	private static final String COLLEGE = "college";
	private static final String YEAR = "year";
	public static LabelInfo queryLabel(UriInfo ui){
		LabelInfo labelInfo = new LabelInfo();
		MultivaluedMap<String,String> map =ui.getQueryParameters();
		for (String key :map.keySet()) {
			switch(key){
			case UNIVERSITY:
				labelInfo.setUniversity(map.get(key).get(0));break;
			case COLLEGE:
				labelInfo.setCollege(map.get(key).get(0));break;
			case YEAR:
				labelInfo.setEntryYear(Integer.valueOf(map.get(key).get(0)));break;
			}
		}
		return labelInfo;
	}
}
