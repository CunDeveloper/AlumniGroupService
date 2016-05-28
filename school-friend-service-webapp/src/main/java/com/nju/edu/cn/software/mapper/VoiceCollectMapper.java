package com.nju.edu.cn.software.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.nju.edu.cn.software.domain.VoiceCollect;

public interface VoiceCollectMapper {

	void saveCollect(VoiceCollect collect);
	int deleteCollect(@Param("id") int id,@Param("authorId")int authorId);
	List<VoiceCollect> getCollects(@Param("authorId") int authorId);
	int isCollected(@Param("id") int id,@Param("authorId") int authorId);
}
