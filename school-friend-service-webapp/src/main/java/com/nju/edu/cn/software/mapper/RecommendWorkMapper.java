package com.nju.edu.cn.software.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.nju.edu.cn.software.domain.CommentAuthorInfo;
import com.nju.edu.cn.software.domain.ContentComment;
import com.nju.edu.cn.software.domain.ReqCommendWork;
import com.nju.edu.cn.software.domain.RespRecommendWork;
import com.nju.edu.cn.software.entity.LabelInfo;

public interface RecommendWorkMapper {

	void saveRecommendWork(ReqCommendWork reqWork);
	void contentComment(ContentComment contentComment);
	int deleteRecommendWork(@Param("id")int id,@Param("author_id") int author_id);
	CommentAuthorInfo queryAuthorById(@Param("id") int id);
	String queryImagePath(@Param("id")int id,@Param("author_id") int author_id);
	
	List<RespRecommendWork> getRecommendWorks(@Param("labels")List<LabelInfo> labels,
			@Param("rowId")int rowId,@Param("limit")int limit,@Param("dir") String dir);
	
	List<RespRecommendWork> getRecommendWorksByAuthorId(@Param("labels")List<LabelInfo> labels,
			@Param("rowId")int rowId,
			@Param("limit")int limit,@Param("dir") String dir,@Param("authorId") int authorId);
	
	List<RespRecommendWork> viewRecommendWorksByType(@Param("labels")List<LabelInfo> labels,@Param("type")int type,
			@Param("rowId")int rowId,@Param("limit")int limit,@Param("dir") String dir);
	List<RespRecommendWork> viewRecommendWorksByCollege(@Param("labels")List<LabelInfo> labels,@Param("college")String college,
			@Param("rowId")int rowId,@Param("limit")int limit,@Param("dir") String dir);
	List<RespRecommendWork> viewRecommendWorksByTypeAndCollege(@Param("labels")List<LabelInfo> labels,@Param("type")int type,@Param("college")String college,
			@Param("rowId")int rowId,@Param("limit")int limit,@Param("dir") String dir);
	//查看自己推荐的工作
	List<RespRecommendWork> viewOwnRecommendWorks(@Param("labels")List<LabelInfo> labels,@Param("authorId")int authorId,
			@Param("rowId")int rowId,@Param("limit")int limit,@Param("dir") String dir);
}
