package com.nju.edu.cn.software.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.nju.edu.cn.software.domain.AlumniTalkComment;
import com.nju.edu.cn.software.domain.CommentAuthorInfo;
import com.nju.edu.cn.software.domain.ContentComment;
import com.nju.edu.cn.software.entity.ReqIdEntity;

public interface AlumniTalkCommentMapper {

	void contentComment(ContentComment contentComment);
	void deleteComment(@Param("id") int id,@Param("commentAuthorId") int commentAuthorId);
	CommentAuthorInfo getCommentAuthorById(@Param("id") int id);
	List<AlumniTalkComment> getTalkCommentsByIds(@Param("ids")List<ReqIdEntity> ids,@Param("authorId") int authorId);
}
