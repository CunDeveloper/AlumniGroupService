package com.nju.edu.cn.software.service;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.Map.Entry;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.glassfish.jersey.media.multipart.FormDataBodyPart;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataMultiPart;
import org.springframework.beans.factory.annotation.Autowired;

import com.nju.edu.cn.software.domain.DynamicCollect;
import com.nju.edu.cn.software.entity.ReqIdEntity;
import com.nju.edu.cn.software.exception.CustomBadRequestException;
import com.nju.edu.cn.software.exception.CustomNotAuthorizedException;
import com.nju.edu.cn.software.helper.Constant;
import com.nju.edu.cn.software.helper.PathConstant;
import com.nju.edu.cn.software.mapper.AuthorizationTokenMapper;
import com.nju.edu.cn.software.mapper.DynamicCollectMapper;
import com.nju.edu.cn.software.util.AppUtil;
import com.nju.edu.cn.software.util.CustomExceptionUtil;
import com.nju.edu.cn.software.util.ExceptionLogUtil;
import com.nju.edu.cn.software.util.FormDataToken;
import com.nju.edu.cn.software.util.UploadImageUtil;

@Path(PathConstant.DYNAMIC_COLLECT)
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)

public class DynamicCollectService {

	private static final Logger log = Logger.getLogger(DynamicCollectService.class);
	 
	
	@Autowired
	private AuthorizationTokenMapper tokenDao;
	@Autowired
	private DynamicCollectMapper collectDao;
	
	@POST
	@Path(PathConstant.SAVE_DYNAMIC_COLLECT)

	public Response  saveDynamicCollect(DynamicCollect collect,@Context ContainerRequestContext crc) throws Exception{
		try{
			int authorId = (int) crc.getProperty(Constant.AUTHOR_ID); 
			String imgPath = collect.getImagePath();
			if(imgPath != null &&!imgPath.trim().equals("")){
				String sourcePath = AppUtil.getAlumniTalkImagePath();
				log.info(sourcePath);
				File sourceFile = new File(sourcePath,imgPath);
				if(sourceFile.exists()){
					String dist = AppUtil.getCollectImagePath();
					log.info(dist);
					File distDir = new File(dist);
					if(!distDir.exists()){
						distDir.mkdirs();
					}
					File destFile = new File(distDir,imgPath);
					if(destFile.createNewFile()){
						log.info("copy file");
						FileUtils.copyFile(sourceFile, destFile);
					}
				}
			}
			collect.setAuthorId(authorId);
			collectDao.saveCollect(collect);
			return CustomExceptionUtil.toResponse();
		}catch(Exception e){
			throw e;
		}
	}
	
	@POST
	@Path(PathConstant.DELETE_DYNAMIC_COLLECT)
	public Response deleteDynamicCollect(ReqIdEntity reqEntity,@Context ContainerRequestContext crc){
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
	@Path(PathConstant.GET_DYNAMIC_COLLECT)
	public Response  getDynamicCollects(@Context ContainerRequestContext crc){
		try{
			int authorId = (int) crc.getProperty(Constant.AUTHOR_ID);
			List<DynamicCollect> collects = collectDao.getCollects(authorId);
			return CustomExceptionUtil.toResponse(collects);
		}catch(Exception e){
			ExceptionLogUtil.recordLog(log, e);
			throw e;
		}
	}
	
}
