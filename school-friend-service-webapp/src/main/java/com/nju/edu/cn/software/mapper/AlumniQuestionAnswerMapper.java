package com.nju.edu.cn.software.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.nju.edu.cn.software.domain.CommentAuthorInfo;
import com.nju.edu.cn.software.domain.ContentComment;
import com.nju.edu.cn.software.entity.ReqIdEntity;

public interface AlumniQuestionAnswerMapper {

	void saveAlumniQuestionAnswer(ContentComment contentComment);
	CommentAuthorInfo getReplayAuthorById(@Param("answerId") int answerId);
	List<ContentComment> getQuestionAnswers(@Param("ids")List<ReqIdEntity> ids);
	
}
