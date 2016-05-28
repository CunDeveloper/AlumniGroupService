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

import com.nju.edu.cn.software.domain.AlumnusVoice;
import com.nju.edu.cn.software.domain.VoiceCollect;
import com.nju.edu.cn.software.entity.ReqCollectIdEntity;
import com.nju.edu.cn.software.entity.ReqIdEntity;
import com.nju.edu.cn.software.helper.Constant;
import com.nju.edu.cn.software.helper.PathConstant;
import com.nju.edu.cn.software.mapper.VoiceCollectMapper;
import com.nju.edu.cn.software.util.CustomExceptionUtil;
import com.nju.edu.cn.software.util.ExceptionLogUtil;

@Path(PathConstant.VOICE_COLLECT)
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)

public class VoiceCollectService {

	private static final Logger log = Logger.getLogger(VoiceCollectService.class);
	 
	@Autowired
	private VoiceCollectMapper collectDao;
	
	@POST
	@Path(PathConstant.SAVE_VOICE_COLLECT)
	public Response  saveVoiceCollect(ReqCollectIdEntity reqEntity,@Context ContainerRequestContext crc){
		try{
			int authorId = (int) crc.getProperty(Constant.AUTHOR_ID);
			VoiceCollect collect = new VoiceCollect();
			collect.setAuthorId(authorId);
			AlumnusVoice voice = new AlumnusVoice();
			voice.setId(reqEntity.getCollectId());
			collect.setAlumnusVoice(voice);
			collectDao.saveCollect(collect);
			return CustomExceptionUtil.toResponse(Constant.OK_MSG);
		}catch(Exception e){
			ExceptionLogUtil.recordLog(log, e);
			throw e;
		} 
	}
	
	@POST
	@Path(PathConstant.DELETE_VOICE_COLLECT)
	public Response deleteVoiceCollect(ReqIdEntity reqEntity,@Context ContainerRequestContext crc){
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
	@Path(PathConstant.GET_VOICE_COLLECT)
	public Response  getVoiceCollects(@Context ContainerRequestContext crc){
		try{
			int authorId = (int) crc.getProperty(Constant.AUTHOR_ID);
			List<VoiceCollect> collects = collectDao.getCollects(authorId);
			return CustomExceptionUtil.toResponse(collects);
		}catch(Exception e){
			ExceptionLogUtil.recordLog(log, e);
			throw e;
		}
	}
	
}
