package com.nju.edu.cn.software.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.nju.edu.cn.software.domain.FriendAlumic;

public interface FriendAlumicMapper {

	List<FriendAlumic> getFriendAlumics(@Param("startIndex")int startIndex,@Param("limitNumber")int limitNumber);
}
