package com.nju.edu.cn.software.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.nju.edu.cn.software.domain.DynamicCollect;

public interface DynamicCollectMapper {

	void saveCollect(DynamicCollect collect);
	int deleteCollect(@Param("id") int id,@Param("authorId") int authorId);
	List<DynamicCollect> getCollects(@Param("authorId") int authorId);
	int isCollected(@Param("id") int id,@Param("authorId") int authorId);
}
