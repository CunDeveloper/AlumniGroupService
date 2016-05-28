package com.nju.edu.cn.software.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.nju.edu.cn.software.domain.AlumnicTalkPraise;
import com.nju.edu.cn.software.entity.ReqIdEntity;

public interface AlumnicTalkPraiseMapper {

	void saveAlumicPraise(AlumnicTalkPraise alumicPraise);
	void cancelPraise(@Param("id") int id,@Param("praiseAuthorId") int praiseAuthorId);
	int isPraised(@Param("contentId")int alumnicTalkId,@Param("authorId")int authorId);
	List<AlumnicTalkPraise> getTalkPraisesByIds(@Param("ids")List<ReqIdEntity> ids,@Param("authorId") int authorId);
	List<AlumnicTalkPraise> getTalkPraisesAboutAuthorByIds(@Param("ids")List<Integer> ids);
}
