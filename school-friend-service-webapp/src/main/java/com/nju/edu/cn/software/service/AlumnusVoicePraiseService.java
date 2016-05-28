package com.nju.edu.cn.software.service;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.nju.edu.cn.software.domain.AlumnusVoicePraise;
import com.nju.edu.cn.software.entity.AuthorInfo;
import com.nju.edu.cn.software.entity.ReqIdEntity;
import com.nju.edu.cn.software.exception.CustomBadRequestException;
import com.nju.edu.cn.software.helper.Constant;
import com.nju.edu.cn.software.helper.PathConstant;
import com.nju.edu.cn.software.mapper.AlumnusVoicePraiseMapper;
import com.nju.edu.cn.software.mapper.AuthorMapper;
import com.nju.edu.cn.software.util.CustomExceptionUtil;
import com.nju.edu.cn.software.util.ExceptionUtil;

@Path(PathConstant.ALUMNUS_VOICE_PRAISE_PATH)
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AlumnusVoicePraiseService {

	private static final Logger log = Logger.getLogger(AlumnusVoicePraiseService.class);
	
	@Autowired
	private AlumnusVoicePraiseMapper alumnsVoicePraiseDao;
	@Autowired
	private AuthorMapper authorDao;
	
	@POST
	@Path(PathConstant.ALUMNUS_VOICE_PRAISE_SUB_PATH_SAVE)
	public Response savePraise(AlumnusVoicePraise alumnusVoicePraise,@Context ContainerRequestContext crc){
		try{
			int authorId = (int) crc.getProperty(Constant.AUTHOR_ID);
			int praiseCount = alumnsVoicePraiseDao.isPraised(alumnusVoicePraise.getContentId(),authorId);
			log.info("praise count " +praiseCount);
			if(praiseCount == 0){
				AuthorInfo author = new AuthorInfo();author.setAuthorId(authorId);
				String authorName = authorDao.getAuthorName(authorId);
				author.setAuthorName(authorName);alumnusVoicePraise.setPraiseAuthor(author);
				alumnsVoicePraiseDao.saveAlumnusVoicePraise(alumnusVoicePraise);
				 
			} else{
				return CustomBadRequestException.toResponse("不能重复点赞");
			}
			 return CustomExceptionUtil.toResponse(alumnusVoicePraise.getId()+"");
		}catch(Exception e){
			ExceptionUtil.recodeException(e, log);
			throw e;
		}
		
	}
	
	@POST
	@Path(PathConstant.ALUMNUS_VOICE_PRAISE_SUB_PATH_CANCEL)
	public Response cancelRecommendWork(ReqIdEntity reqEntity,@Context ContainerRequestContext crc){
		try{
			int authorId = (int) crc.getProperty(Constant.AUTHOR_ID);
			alumnsVoicePraiseDao.cancelAlumnusVoicePraise(reqEntity.getId(),authorId);
			return CustomExceptionUtil.toResponse(Constant.OK_MSG);
		}catch(Exception e){
			ExceptionUtil.recodeException(e, log);
			throw e;
		}
		
	}
}
