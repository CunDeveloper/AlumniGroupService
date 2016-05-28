package com.nju.edu.cn.software.util;

import java.util.ArrayList;
import java.util.List;

import com.nju.edu.cn.software.entity.LabelInfo;
import com.nju.edu.cn.software.helper.Constant;
import com.nju.edu.cn.software.mapper.UserDegreeInfoMapper;

public class LabelInfoUtil {

	public static List<LabelInfo> getLabels(UserDegreeInfoMapper degreeInfoDao,String level,int authorId){
		List<LabelInfo> resultLabels = new ArrayList<>();
		List<LabelInfo> labels = degreeInfoDao.getLabelInfosByAuthorId(authorId);
		switch(level){
		case Constant.LEVEL_ALL:
			return labels;
		case Constant.LEVEL_UNDERGRADUATE:
		{
			LabelInfo temInfo =getLabel(labels,level);
			if(temInfo!=null){
				resultLabels.add(temInfo);
				return resultLabels;
			}
			break;
		}
		case Constant.LEVEL_DOCTOR:
		{
			LabelInfo temInfo =getLabel(labels,level);
			if(temInfo!=null){
				resultLabels.add(temInfo);
				return resultLabels;
			}
			break;
		}
		case Constant.LEVEL_MASTER:
		{
			LabelInfo temInfo =getLabel(labels,level);
			if(temInfo!=null){
				resultLabels.add(temInfo);
				return resultLabels;
			}
			break;
		}
		}
		return resultLabels;
	}
	
	private static LabelInfo getLabel(List<LabelInfo> labels,final String level){
		LabelInfo resultLable = null;
		for(LabelInfo label:labels){
			if(label.getLevel().trim().equals(level)){
				resultLable = label;
			}
		}
		return resultLable;
	}
}
