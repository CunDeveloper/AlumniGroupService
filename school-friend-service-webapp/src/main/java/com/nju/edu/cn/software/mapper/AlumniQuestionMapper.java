package com.nju.edu.cn.software.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.nju.edu.cn.software.domain.AlumniQuestion;
import com.nju.edu.cn.software.domain.CommentAuthorInfo;
import com.nju.edu.cn.software.domain.RespAlumniQuestion;
import com.nju.edu.cn.software.entity.LabelInfo;
import com.nju.edu.cn.software.entity.ReqAlumniQuestion;

public interface AlumniQuestionMapper {

	void saveAnswerQuestion(ReqAlumniQuestion reqQuestion);
	void deleteAnswerQuestion(@Param("id")int id,@Param("authorId")int authorId);
	String queryImagePath(@Param("id")int id,@Param("author_id") int author_id);
	CommentAuthorInfo queryAuthorById(@Param("id") int id);
	void closeQuestion(@Param("id")int id,@Param("authorId")int authorId);
	int isClosedQuestion(@Param("id")int id);
	List<RespAlumniQuestion> getAlumniQuestions(@Param("labels")List<LabelInfo> labels,
			@Param("rowId")int rowId,@Param("limit")int limit,@Param("dir") String dir);
	
	List<RespAlumniQuestion> getAlumniQuestionsByAuthorId(@Param("labels")List<LabelInfo> labels,
			@Param("rowId")int rowId,@Param("limit")int limit,@Param("dir") String dir
			,@Param("authorId") int authorId);
	
	List<AlumniQuestion> viewOwnAlumniQuestions(@Param("labels")List<LabelInfo> labels,@Param("authorId")int authorId,
			@Param("rowId")int rowId,@Param("limit")int limit,@Param("dir") String dir);
	List<RespAlumniQuestion> viewAlumniQuestionsByCollege(@Param("labels")List<LabelInfo> labels,@Param("college")String college,
			@Param("rowId")int rowId,@Param("limit")int limit,@Param("dir") String dir);
	List<RespAlumniQuestion> viewAlumniQuestionsByYear(@Param("labels")List<LabelInfo> labels,@Param("year")int year,
			@Param("rowId")int rowId,@Param("limit")int limit,@Param("dir") String dir);
	List<RespAlumniQuestion> viewAlumniQuestionsByLabel(@Param("labels")List<LabelInfo> labels,@Param("askLabel") String askLabel ,
			@Param("rowId")int rowId,@Param("limit")int limit,@Param("dir") String dir);
	List<String> getLabels();
}
