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

import com.nju.edu.cn.software.domain.UserDegreeInfo;
import com.nju.edu.cn.software.entity.AuthorInfo;
import com.nju.edu.cn.software.helper.Constant;
import com.nju.edu.cn.software.helper.PathConstant;
import com.nju.edu.cn.software.mapper.UserDegreeInfoMapper;
import com.nju.edu.cn.software.util.CustomExceptionUtil;
import com.nju.edu.cn.software.util.ExceptionUtil;

@Path(PathConstant.USER_DEGREE_INFO_PATH)
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserDegreeInfoService {

	private static final Logger log = Logger.getLogger(UserDegreeInfoService.class);
	@Autowired
	private UserDegreeInfoMapper degreeInfoDao;
	
	@POST
	@Path(PathConstant.USER_DEGREE_INFO_SUB_PATH_SAVE)
	public Response  saveDegreeInfo(UserDegreeInfo degreeInfo){
		try{
			degreeInfoDao.saveUserDegreeInfo(degreeInfo);
			return CustomExceptionUtil.toResponse();
		}catch(Exception e){
			ExceptionUtil.recodeException(e, log);
			throw e;
		}
	}
	
	@POST
	@Path(PathConstant.USER_DEGREE_INFO_QUERY)
	public Response  query(@Context ContainerRequestContext crc){
		try{
			int authorId = (int) crc.getProperty(Constant.AUTHOR_ID);
			List<UserDegreeInfo> degrees = degreeInfoDao.getUserDegrees(authorId);
			return CustomExceptionUtil.toResponse(degrees);
		}catch(Exception e){
			ExceptionUtil.recodeException(e, log);
			throw e;
		}
		
	}
	
	@POST
	@Path(PathConstant.USER_DEGREE_INFO_GET_OTHER)
	public Response  queryOtherAuthorDegrees(AuthorInfo author){
		try{
			List<UserDegreeInfo> degrees = degreeInfoDao.getOtherUserDegrees(author.getAuthorId(),author.getAuthorName());
			return CustomExceptionUtil.toResponse(degrees);
		}catch(Exception e){
			ExceptionUtil.recodeException(e, log);
			throw e;
		}
		
	}
	
	@POST
	@Path(PathConstant.USER_DEGREE_IS_AUTHORIZATION)
	public Response  isAuthorization(@Context ContainerRequestContext crc){
		try{
			int authorId = (int) crc.getProperty(Constant.AUTHOR_ID);
			int count = degreeInfoDao.isAuthorization(authorId);
			return CustomExceptionUtil.toResponse(count);
		}catch(Exception e){
			ExceptionUtil.recodeException(e, log);
			throw e;
		}
		
	}
	
}
