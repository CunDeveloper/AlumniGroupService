package com.nju.edu.cn.software.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.nju.edu.cn.software.domain.CommentAuthorInfo;
import com.nju.edu.cn.software.domain.ContentComment;
import com.nju.edu.cn.software.entity.ReqIdEntity;

public interface AlumnusVoiceCommentMapper {

	void contentComment(ContentComment contentComment);
	void cancelAlumnusVoiceComment(@Param("id") int id,@Param("commentAuthorId") int commentAuthorId);
	CommentAuthorInfo getCommentAuthorById(@Param("id") int id);
	List<ContentComment> getCommentsByVoiceId(@Param("ids")List<ReqIdEntity> ids);
}
