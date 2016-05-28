package com.nju.edu.cn.software.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.nju.edu.cn.software.domain.AlumniTalk;
import com.nju.edu.cn.software.domain.CommentAuthorInfo;
import com.nju.edu.cn.software.domain.RespAlumnicTalk;
import com.nju.edu.cn.software.domain.RespOwnAlumnicTalk;
import com.nju.edu.cn.software.entity.LabelInfo;
import com.nju.edu.cn.software.entity.ReqAlumniTalk;

public interface AlumniTalkMapper {

	void saveContent(ReqAlumniTalk reqAlumniTalk);
	void cancelContent(@Param("id")int id,@Param("author_id") int author_id);
	String queryImagePaths(@Param("id")int id,@Param("author_id") int author_id);
	List<AlumniTalk> getContentsByAuthor(int authorId);
	
	List<RespAlumnicTalk> getAlumniTalks(@Param("labels")List<LabelInfo> labels,
			@Param("rowId")int rowId,@Param("limit")int limit,@Param("dir") String dir);
	List<RespAlumnicTalk> getAlumniTalksByAuthorId(@Param("labels")List<LabelInfo> labels,
			@Param("rowId")int rowId,@Param("limit")int limit,@Param("dir") String dir,@Param("authorId") int authorId);
	
	List<RespOwnAlumnicTalk> viewOwnAlumniTalks(@Param("labels")List<LabelInfo> labels,
			@Param("authorId")int authorId,@Param("rowId")int rowId,@Param("limit")int limit,@Param("dir") String dir);
	List<RespAlumnicTalk> viewAlumniTalksByCollege(@Param("labels")List<LabelInfo> labels,@Param("college")String college,@Param("rowId")int rowId,@Param("limit")int limit,@Param("dir") String dir);
	List<RespAlumnicTalk> viewAlumniTalksByYear(@Param("labels")List<LabelInfo> labels,@Param("year")int year,@Param("rowId")int rowId,@Param("limit")int limit,@Param("dir") String dir);
	List<RespAlumnicTalk> viewMostHotTalk(@Param("labels")List<LabelInfo> labels,@Param("offset")int offset,@Param("limit")int limit);
	CommentAuthorInfo queryAuthorById(@Param("id") int id);
	List<Integer> getTalkIdByAuthorId(@Param("authorId")int authorId);
}
