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

import com.nju.edu.cn.software.domain.AlumnicTalkPraise;
import com.nju.edu.cn.software.domain.AuthorInfoShort;
import com.nju.edu.cn.software.entity.AuthorInfo;
import com.nju.edu.cn.software.entity.ReqIdEntity;
import com.nju.edu.cn.software.helper.Constant;
import com.nju.edu.cn.software.helper.PathConstant;
import com.nju.edu.cn.software.mapper.AlumnicTalkPraiseMapper;
import com.nju.edu.cn.software.mapper.AuthorMapper;
import com.nju.edu.cn.software.util.CustomExceptionUtil;
import com.nju.edu.cn.software.util.ExceptionUtil;

@Path(PathConstant.ALUMNI_TALK_PRAISE_PATH)
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AlumnicTalkPraiseService {

	private static final Logger log = Logger.getLogger(AlumnicTalkPraiseService.class);
	
	@Autowired
	private AlumnicTalkPraiseMapper alumicPraiseDao;
	@Autowired
	private AuthorMapper authorDao;
	
	@POST
	@Path(PathConstant.ALUMNI_TALK_PRAISE_SUB_PATH_SAVE)
	public Response saveAlumicPraise(AlumnicTalkPraise alumicPraise,@Context ContainerRequestContext crc){
		try{
			int authorId = (int) crc.getProperty(Constant.AUTHOR_ID);
			int praiseCount = alumicPraiseDao.isPraised(alumicPraise.getContentId(),authorId);
			log.info("praise count " +praiseCount);
			if(praiseCount == 0){
				AuthorInfo author = new AuthorInfo();author.setAuthorId(authorId);
				String authorName = authorDao.getAuthorName(authorId);
				author.setAuthorName(authorName);alumicPraise.setPraiseAuthor(author);
				alumicPraiseDao.saveAlumicPraise(alumicPraise);
			}else{
				return CustomExceptionUtil.toResponse(Constant.CODE_202,Constant.REPEATE_OPERATE_MSG);
			}
			 
		    return CustomExceptionUtil.toResponse();
		}catch(Exception e){
			ExceptionUtil.recodeException(e, log);
			throw e;
		}
		
	}
	
	@POST
	@Path(PathConstant.ALUMNI_TALK_PRAISE_SUB_PATCH_CANCEL)
	public Response cancleAlumicPraise(ReqIdEntity reqEntity,@Context ContainerRequestContext crc){
		try{
			int authorId = (int) crc.getProperty(Constant.AUTHOR_ID);
			alumicPraiseDao.cancelPraise(reqEntity.getId(),authorId);
			return CustomExceptionUtil.toResponse();
		}catch(Exception e){
			ExceptionUtil.recodeException(e, log);
			throw e;
		}
		
	}
}
