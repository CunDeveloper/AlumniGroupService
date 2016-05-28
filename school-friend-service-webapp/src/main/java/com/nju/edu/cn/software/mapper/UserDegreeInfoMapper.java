package com.nju.edu.cn.software.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.nju.edu.cn.software.domain.SchoolInfoShort;
import com.nju.edu.cn.software.domain.UserDegreeInfo;
import com.nju.edu.cn.software.entity.LabelInfo;

public interface UserDegreeInfoMapper {

	void saveUserDegreeInfo(UserDegreeInfo degreeInfo);
	LabelInfo getLabelInfoByAuthorId(@Param("authorId")int authorId);
	List<UserDegreeInfo> getUserDegrees(@Param("authorId") int authorId);
	List<UserDegreeInfo> getOtherUserDegrees(@Param("authorId") int authorId,@Param("authorName") String authorName);
	List<UserDegreeInfo> getDegreeInfos(LabelInfo label);
	List<LabelInfo> getLabelInfosByAuthorId(@Param("authorId")int authorId);
	List<String> getDegreeInfoLevel(@Param("authorId")int authorId);
	List<SchoolInfoShort> getUniversitys(@Param("authorId") int authorId);
	
	int isAuthorization(@Param("authorId") int authorId);
}
