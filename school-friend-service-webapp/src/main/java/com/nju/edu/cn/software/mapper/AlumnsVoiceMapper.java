package com.nju.edu.cn.software.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.nju.edu.cn.software.domain.AlumnusVoice;
import com.nju.edu.cn.software.domain.CommentAuthorInfo;
import com.nju.edu.cn.software.domain.RespAlumnicVoice;
import com.nju.edu.cn.software.entity.LabelInfo;
import com.nju.edu.cn.software.entity.ReqAlumnusVoice;

public interface AlumnsVoiceMapper {

	void saveAlumnsVoice(ReqAlumnusVoice alumnsVoice);
	void cancelAlumnsVoice(@Param("id")int id,@Param("author_id") int author_id);
	String queryImagePath(@Param("id")int id,@Param("author_id") int author_id);
	List<RespAlumnicVoice> getAlumnusVoices(@Param("labels")List<LabelInfo> labels,
			@Param("rowId")int rowId,@Param("limit")int limit,
			@Param("dir") String dir);
	
	List<RespAlumnicVoice> getAlumnusVoicesByAuthorId(@Param("labels")List<LabelInfo> labels,
			@Param("rowId")int rowId,@Param("limit")int limit,
			@Param("dir") String dir,@Param("authorId") int authorId);
	
	List<RespAlumnicVoice> getAlumnusVoicesByTypes(@Param("labels")List<LabelInfo> labels,
			@Param("label")String label,@Param("rowId")int rowId,@Param("limit")int limit,@Param("dir") String dir);
	
	List<RespAlumnicVoice> getAlumnusVoicesByCollege(@Param("labels")List<LabelInfo> labels,
			 @Param("college")String college,
			 @Param("rowId")int rowId,@Param("limit")int limit,@Param("dir") String dir);
	
	List<RespAlumnicVoice> getAlumnusVoicesByTypesAndCollege(@Param("labels")List<LabelInfo> labels,
			@Param("label")String label,@Param("college")String college,
			@Param("rowId")int rowId,@Param("limit")int limit,@Param("dir") String dir);
	
	List<AlumnusVoice> viewOwnVoices(@Param("labels")List<LabelInfo> labels,@Param("authorId")int authorId,
			@Param("rowId")int rowId,@Param("limit")int limit,@Param("dir") String dir);
	
	CommentAuthorInfo queryAuthorById(@Param("id") int id);
	List<String> getVoiceLabel();
}
