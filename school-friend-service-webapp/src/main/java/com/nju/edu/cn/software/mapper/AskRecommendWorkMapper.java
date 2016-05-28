package com.nju.edu.cn.software.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.nju.edu.cn.software.domain.CommentAuthorInfo;
import com.nju.edu.cn.software.domain.ContentComment;
import com.nju.edu.cn.software.entity.ReqIdEntity;

public interface AskRecommendWorkMapper {
	
	CommentAuthorInfo getAskAuthorById(@Param("askRecommendWorkId") int askRecommendWorkId);
	List<ContentComment> getAskAboutRecommends(@Param("ids")List<ReqIdEntity> ids);
}
