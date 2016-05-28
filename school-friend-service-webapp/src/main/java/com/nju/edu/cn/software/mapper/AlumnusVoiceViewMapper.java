package com.nju.edu.cn.software.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.nju.edu.cn.software.domain.AlumnusVoiceView;

public interface AlumnusVoiceViewMapper {

	List<AlumnusVoiceView> getAlumnusVoiceByUniversity(@Param("offset")int offset,@Param("limit")int limit);
	List<AlumnusVoiceView> getAlumnusVoiceByCollege(@Param("college")String college,@Param("offset")int offset,@Param("limit")int limit);
}
