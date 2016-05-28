package com.nju.edu.cn.software.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.nju.edu.cn.software.domain.AlumnusVoicePraise;
import com.nju.edu.cn.software.entity.ReqIdEntity;

public interface AlumnusVoicePraiseMapper {

	void saveAlumnusVoicePraise(AlumnusVoicePraise alumnusVoicePraise);
	void cancelAlumnusVoicePraise(@Param("id") int id,@Param("praiseAuthorId") int praiseAuthorId);
	/**
	 * 
	 * @param authorId
	 * @return 0 represent not praised.>0 represent have been praised
	 */
	int isPraised(@Param("contentId") int contentId,@Param("authorId") int authorId);
	
	List<AlumnusVoicePraise> getPraisesByVoiceId(@Param("ids")List<ReqIdEntity> ids);
}
