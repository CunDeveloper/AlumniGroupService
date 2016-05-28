package com.nju.edu.cn.software.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface CollegeMapper {

	List<String> getColleageByUniversity(@Param("name")String name);
}
