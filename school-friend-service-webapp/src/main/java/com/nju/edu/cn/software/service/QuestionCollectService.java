package com.nju.edu.cn.software.service;

import java.util.List;

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

import com.nju.edu.cn.software.domain.AlumniQuestion;
import com.nju.edu.cn.software.domain.QuestionCollect;
import com.nju.edu.cn.software.entity.ReqCollectIdEntity;
import com.nju.edu.cn.software.entity.ReqIdEntity;
import com.nju.edu.cn.software.helper.Constant;
import com.nju.edu.cn.software.helper.PathConstant;
import com.nju.edu.cn.software.mapper.QuestionCollectMapper;
import com.nju.edu.cn.software.util.CustomExceptionUtil;
import com.nju.edu.cn.software.util.ExceptionLogUtil;

@Path(PathConstant.QUESTION_COLLECT)
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)

public class QuestionCollectService {

	private static final Logger log = Logger.getLogger(QuestionCollectService.class);
	 
	@Autowired
	private QuestionCollectMapper collectDao;
	
	@POST
	@Path(PathConstant.SAVE_QUESTION_COLLECT)
	public Response  saveQuestionCollect(ReqCollectIdEntity reqEntity,@Context ContainerRequestContext crc){
		try{
			int authorId = (int) crc.getProperty(Constant.AUTHOR_ID);
			QuestionCollect collect = new QuestionCollect();
			collect.setAuthorId(authorId);
			AlumniQuestion alumniQuestion = new AlumniQuestion();
			alumniQuestion.setId(reqEntity.getCollectId());
			collect.setAlumniQuestion(alumniQuestion);
			collectDao.save(collect);
			return CustomExceptionUtil.toResponse(Constant.OK_MSG);
		}catch(Exception e){
			ExceptionLogUtil.recordLog(log, e);
			throw e;
		} 
	}
	
	@POST
	@Path(PathConstant.DELETE_QUESTION_COLLECT)
	public Response deleteQuestionCollect(ReqIdEntity reqEntity,@Context ContainerRequestContext crc){
		try{
			int authorId = (int) crc.getProperty(Constant.AUTHOR_ID);
			collectDao.deleteCollect(reqEntity.getId(),authorId);
			return CustomExceptionUtil.toResponse();
		}catch(Exception e){
			ExceptionLogUtil.recordLog(log, e);
			throw e;
		}
	}
	
	@POST
	@Path(PathConstant.IS_COLLECTED)
	public Response isCollected(ReqIdEntity reqEntity,@Context ContainerRequestContext crc){
		try{
			int authorId = (int) crc.getProperty(Constant.AUTHOR_ID);
			int count = collectDao.isCollected(reqEntity.getId(),authorId);
			return CustomExceptionUtil.toResponse(count);
		}catch(Exception e){
			ExceptionLogUtil.recordLog(log, e);
			throw e;
		}
	}
	
	@POST
	@Path(PathConstant.GET_QUESTION_COLLECT)
	public Response  getQuestionCollects(@Context ContainerRequestContext crc){
		try{
			int authorId = (int) crc.getProperty(Constant.AUTHOR_ID);
			List<QuestionCollect> collects = collectDao.getCollects(authorId);
			return CustomExceptionUtil.toResponse(collects);
		}catch(Exception e){
			ExceptionLogUtil.recordLog(log, e);
			throw e;
		}
	}
	
}
