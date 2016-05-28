package com.nju.edu.cn.software.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.nju.edu.cn.software.domain.QuestionCollect;

public interface QuestionCollectMapper {

	void save(QuestionCollect collect);
	int deleteCollect(@Param("id")int id,@Param("authorId")int authorId);
	List<QuestionCollect> getCollects(@Param("authorId")int authorId);
	int isCollected(@Param("id") int id,@Param("authorId") int authorId);
}
