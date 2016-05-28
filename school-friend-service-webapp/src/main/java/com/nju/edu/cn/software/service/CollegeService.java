package com.nju.edu.cn.software.service;

import java.util.HashMap;
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

import com.nju.edu.cn.software.domain.SchoolInfoShort;
import com.nju.edu.cn.software.helper.Constant;
import com.nju.edu.cn.software.helper.PathConstant;
import com.nju.edu.cn.software.mapper.CollegeMapper;
import com.nju.edu.cn.software.mapper.UserDegreeInfoMapper;
import com.nju.edu.cn.software.util.CustomExceptionUtil;
import com.nju.edu.cn.software.util.ExceptionUtil;

@Path(PathConstant.COLLEGE_PATH)
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CollegeService {

	private static final Logger log = Logger.getLogger(CollegeService.class);
	@Autowired
	private UserDegreeInfoMapper degreeInfoDao;
	@Autowired
	private CollegeMapper collegeDao;
	
	@POST
	@Path(PathConstant.COLLEGE_SUB_PATH_GET_SCHOOLS)
	public Response  getCollegesForUser(@Context ContainerRequestContext crc){
		try{
			int authorId = (int) crc.getProperty(Constant.AUTHOR_ID);
			List<SchoolInfoShort> shorts = degreeInfoDao.getUniversitys(authorId);
			HashMap<String,List<String>> colleges = new HashMap<>();
			for(SchoolInfoShort infoShort:shorts){
				colleges.put(infoShort.getLevel(),
						collegeDao.getColleageByUniversity(infoShort.getUniversityName()));
			}
			return CustomExceptionUtil.toResponse(colleges);
		}catch(Exception e){
			ExceptionUtil.recodeException(e, log);
			throw e;
		}
		
	}
	
}
