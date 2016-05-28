package com.nju.edu.cn.software.service;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.Map.Entry;

import javax.validation.constraints.NotNull;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.apache.log4j.Logger;
import org.glassfish.jersey.media.multipart.FormDataBodyPart;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataMultiPart;
import org.springframework.beans.factory.annotation.Autowired;

import com.nju.edu.cn.software.domain.AlumniTalk;
import com.nju.edu.cn.software.domain.AlumniTalkComment;
import com.nju.edu.cn.software.domain.AlumnicTalkPraise;
import com.nju.edu.cn.software.domain.RespAlumnicTalk;
import com.nju.edu.cn.software.domain.RespOwnAlumnicTalk;
import com.nju.edu.cn.software.entity.LabelInfo;
import com.nju.edu.cn.software.entity.ReqAlumniTalk;
import com.nju.edu.cn.software.entity.ReqIdEntity;
import com.nju.edu.cn.software.entity.ReqQueryEntity;
import com.nju.edu.cn.software.exception.CustomBadRequestException;
import com.nju.edu.cn.software.exception.CustomNotAuthorizedException;
import com.nju.edu.cn.software.exception.CustomNotSupportedException;
import com.nju.edu.cn.software.helper.Constant;
import com.nju.edu.cn.software.helper.PathConstant;
import com.nju.edu.cn.software.mapper.AlumniTalkCommentMapper;
import com.nju.edu.cn.software.mapper.AlumniTalkMapper;
import com.nju.edu.cn.software.mapper.AlumnicTalkPraiseMapper;
import com.nju.edu.cn.software.mapper.AuthorizationTokenMapper;
import com.nju.edu.cn.software.mapper.UserDegreeInfoMapper;
import com.nju.edu.cn.software.util.AppUtil;
import com.nju.edu.cn.software.util.CustomExceptionUtil;
import com.nju.edu.cn.software.util.ExceptionUtil;
import com.nju.edu.cn.software.util.FormDataToken;
import com.nju.edu.cn.software.util.LabelInfoUtil;
import com.nju.edu.cn.software.util.UploadImageUtil;

@Path(PathConstant.ALUMNI_TALK_PATH)
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class AlumniTalkService {
	private static final Logger log = Logger.getLogger(AlumniTalkService.class);
	
	private static final String FILE = "file";
	private static final String CONTENT = "content";
	private static final String WHO_SCAN = "whoScan";
	private static final String LOCATION ="location";
	@Autowired
	private AlumniTalkMapper alumniTalkDao;
	@Autowired
	private AuthorizationTokenMapper tokenDao;
	@Autowired
	private UserDegreeInfoMapper degreeInfoDao;
	@Autowired
	private AlumnicTalkPraiseMapper talkPraiseDao;
	@Autowired
	private AlumniTalkCommentMapper talkCommentDao;
	@Context private UriInfo info;

	@POST
	@Path(PathConstant.ALUMNI_TALK_SUB_PATH_SAVE)
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public Response saveContent(FormDataMultiPart multiPart){
		try{
			int authorId = new FormDataToken().tokenValidate(multiPart,tokenDao);
			List<String> labelList =info.getQueryParameters().get(Constant.LEVEL);
			if(authorId != 0 && labelList!=null){
				String level =labelList.get(0);
				List<LabelInfo> labels =LabelInfoUtil.getLabels(degreeInfoDao, level, authorId);
				if(labels.size()!=0){
					AlumniTalk alumniTalk = new AlumniTalk();
					Map<String,List<FormDataBodyPart>> maps = multiPart.getFields();
					List<String> images = new ArrayList<String>();
			         for(Entry<String,List<FormDataBodyPart>> entry:maps.entrySet()){
			        	 if (entry.getKey().equals(FILE)){
			        		 List<FormDataBodyPart> fileBodyParts =entry.getValue();
			        		 for(FormDataBodyPart bodyPart:fileBodyParts){
			        			 FormDataContentDisposition contentDisposition=bodyPart.getFormDataContentDisposition();
			        			 String uploadFileName = contentDisposition.getFileName();
			        			 InputStream in = bodyPart.getEntityAs(InputStream.class);
			        			 final long size =0;
			        			 if(UploadImageUtil.isRightImage(uploadFileName,size)){
			        				 UUID uuid = UUID.randomUUID();
				        			 StringBuilder builder = new StringBuilder();
				        			 for(String str:(uuid+"").split("-")){
				        				 builder.append(str);
				        	          }
				        			 final String fileName = builder.toString()+"."+Constant.JPG; 
				        			 images.add(fileName);
				        			 final String path = AppUtil.getAlumniTalkImagePath();
				            		 UploadImageUtil.save(path,fileName,in, contentDisposition); 
			        			 }
			        			 else{
			        				 return CustomNotSupportedException.toResponse("upload file type or size error:"+uploadFileName+""
			        				 		+ "file size:"+size+"\n"+
			        						 "just only upload img format and max size is 2 M");
			        			 }
			        		 }
			        		 alumniTalk.setImages(images);
			        	 
			        	 } else {
			        		 String filedName = entry.getKey();
			        		 switch(filedName){
			        		 case CONTENT:
			        			 alumniTalk.setContent(entry.getValue().get(0).getValue());
			        			 break;
			        		 case WHO_SCAN:
			        			 alumniTalk.setWhoScan(Integer.valueOf(entry.getValue().get(0).getValue()));
			        			 break;
			        		 case LOCATION:
			        			 alumniTalk.setLocation(entry.getValue().get(0).getValue());
			        			 break;
			        		 }
			        	 }
			         }
			        ReqAlumniTalk reqAlumniTalk = new ReqAlumniTalk();
			        reqAlumniTalk.setAlumnicTalk(alumniTalk);
			        reqAlumniTalk.setLabelList(labels);
			        alumniTalkDao.saveContent(reqAlumniTalk);
			        return CustomExceptionUtil.toResponse();
				}
		        else{
					return CustomBadRequestException.toResponseInvalidParams();
				}
			} else{
				if(authorId ==0)
					return CustomNotAuthorizedException.toResponse("not authorized");
				return CustomBadRequestException.toResponseInvalidParams();
			}
		}catch(Exception e){
			ExceptionUtil.recodeException(e, log);
			throw e;
		}
	}
	
	@POST
	@Path(PathConstant.ALUMNI_TALK_SUB_PATH_CANCEL)
	public Response cancleContent(ReqIdEntity reqEntity,@Context ContainerRequestContext crc){
		try{
			int authorId = (int) crc.getProperty(Constant.AUTHOR_ID);
			alumniTalkDao.queryImagePaths(reqEntity.getId(),authorId);
			//UploadImageUtil.deleteImage(AppUtil.getAlumniTalkImagePath(),imgPath);
			alumniTalkDao.cancelContent(reqEntity.getId(),authorId);
			return CustomExceptionUtil.toResponse();
		}catch(Exception e){
			ExceptionUtil.recodeException(e, log);
			throw e;
		}
	}

	@POST
	@Path(PathConstant.ALUMNI_TALK_SUB_PATH_GET_CONTENTS_BY_AUTHOR)
	public Response getContentsByAuthor(int author_id){
		try{
			List<AlumniTalk> alumniTalks = alumniTalkDao.getContentsByAuthor(author_id);
			return CustomExceptionUtil.toResponse(alumniTalks);
		}catch(Exception e){
			ExceptionUtil.recodeException(e, log);
			throw e;
		}
	}
	
	@POST
	@Path(PathConstant.ALUMNI_TALK_SUB_PATH_VIEW_OWN_TALKS)
	public Response viewOwnTalks(ReqQueryEntity reqEntity,
			@Context ContainerRequestContext crc,
			@NotNull @QueryParam("level")String level){
		try{
			log.info(level);
			int authorId = (int) crc.getProperty(Constant.AUTHOR_ID);
			List<LabelInfo> labels =LabelInfoUtil.getLabels(degreeInfoDao, level, authorId);
			if(labels.size()>=1){
				List<RespOwnAlumnicTalk> alumnicTalk = alumniTalkDao.viewOwnAlumniTalks(labels,authorId,
						reqEntity.getRowId(),reqEntity.getLimit(),reqEntity.getDir());
				return CustomExceptionUtil.toResponse(alumnicTalk);
			}else{
				return CustomBadRequestException.toResponseInvalidParams();
			}
			
		}catch(Exception e){
			ExceptionUtil.recodeException(e, log);
			throw e;
		}
	}
	
	
	@POST
	@Path(PathConstant.ALUMNI_TALK_SUB_PATH_VIEW_ALL)
	public Response viewTalks(ReqQueryEntity reqEntity,
			@Context ContainerRequestContext crc,
			@NotNull @QueryParam("level")String level){
		try{
			log.info(level);
			int authorId = (int) crc.getProperty(Constant.AUTHOR_ID);
			List<LabelInfo> labels =LabelInfoUtil.getLabels(degreeInfoDao, level, authorId);
			if(labels.size()>=1){
				List<RespAlumnicTalk> alumnicTalk = alumniTalkDao.getAlumniTalks(labels,reqEntity.getRowId(),reqEntity.getLimit(),reqEntity.getDir());
				return CustomExceptionUtil.toResponse(alumnicTalk);
			}else{
				return CustomBadRequestException.toResponseInvalidParams();
			}
		}catch(Exception e){
			ExceptionUtil.recodeException(e, log);
			throw e;
		}
	}
	
	@POST
	@Path(PathConstant.ALUMNI_TALK_SUB_PATH_GET_TALKS_BY_AUTHORID)
	public Response viewTalksByAuthorId(ReqQueryEntity reqEntity,
			@Context ContainerRequestContext crc){
		try{
			int authorId = (int) crc.getProperty(Constant.AUTHOR_ID);
			List<LabelInfo> labels =LabelInfoUtil.getLabels(degreeInfoDao,Constant.LEVEL_ALL, authorId);
			if(labels.size()>=1){
				List<RespAlumnicTalk> alumnicTalk = alumniTalkDao.getAlumniTalksByAuthorId(labels,reqEntity.getRowId(),reqEntity.getLimit(),
						reqEntity.getDir(),reqEntity.getAuthorId());
				return CustomExceptionUtil.toResponse(alumnicTalk);
			}else{
				return CustomBadRequestException.toResponseInvalidParams();
			}
		}catch(Exception e){
			ExceptionUtil.recodeException(e, log);
			throw e;
		}
	}
	
	
	@POST
	@Path(PathConstant.ALUMNI_TALK_SUB_PATH_VIEW_TALKS_BY_COLLEGE)
	public Response viewTalksByCollege(ReqQueryEntity reqEntity,
			@Context ContainerRequestContext crc,
			@NotNull @QueryParam("level")String level){
		try{
			log.info(level);
			int authorId = (int) crc.getProperty(Constant.AUTHOR_ID);
			List<LabelInfo> labels =LabelInfoUtil.getLabels(degreeInfoDao, level, authorId);
			if(labels.size()>=1){
				List<RespAlumnicTalk> alumnicTalk = alumniTalkDao.viewAlumniTalksByCollege(labels,reqEntity.getCollege(),
						reqEntity.getRowId(),reqEntity.getLimit(),reqEntity.getDir());
				return CustomExceptionUtil.toResponse(alumnicTalk);
			}else{
				return CustomBadRequestException.toResponseInvalidParams();
			}
		}catch(Exception e){
			ExceptionUtil.recodeException(e, log);
			throw e;
		}
	}
	
	@POST
	@Path(PathConstant.ALUMNI_TALK_SUB_PATH_VIEW_TALKS_BY_YEAR)
	public Response viewTalksByYear(ReqQueryEntity reqEntity,
			@Context ContainerRequestContext crc,
			@NotNull @QueryParam("level")String level){
		try{
			log.info(level);
			int authorId = (int) crc.getProperty(Constant.AUTHOR_ID);
			List<LabelInfo> labels =LabelInfoUtil.getLabels(degreeInfoDao, level, authorId);
			log.info(reqEntity.getEntryYear());
			if(labels.size()>=1){
				List<RespAlumnicTalk> alumnicTalk = alumniTalkDao.viewAlumniTalksByYear(labels,reqEntity.getEntryYear(),reqEntity.getRowId(),
						reqEntity.getLimit(),reqEntity.getDir());
				return CustomExceptionUtil.toResponse(alumnicTalk);
			}else{
				return CustomBadRequestException.toResponseInvalidParams();
			}
		}catch(Exception e){
			ExceptionUtil.recodeException(e, log);
			throw e;
		}
	}
	
	
	@POST
	@Path(PathConstant.ALUMNI_TALK_SUB_PATH_VIEW_MOST_TALKS)
	public Response viewMostHotTalks(ReqQueryEntity reqEntity,
			@Context ContainerRequestContext crc,
			@NotNull @QueryParam("level")String level){
		try{
			log.info(level);
			int authorId = (int) crc.getProperty(Constant.AUTHOR_ID);
			List<LabelInfo> labels =LabelInfoUtil.getLabels(degreeInfoDao, level, authorId);
			if(labels.size()>=1){
				List<RespAlumnicTalk> alumnicTalk = alumniTalkDao.viewMostHotTalk(labels,reqEntity.getOffset(),reqEntity.getTotal());
				return CustomExceptionUtil.toResponse(alumnicTalk);
			}else{
				return CustomBadRequestException.toResponseInvalidParams();
			}
		}catch(Exception e){
			ExceptionUtil.recodeException(e, log);
			throw e;
		}
		
	}
	
	@POST
	@Path(PathConstant.ALUMNI_TALK_SUB_PATH_VIEW_GET_PRAISE)
	public Response getTalkPraiseByIds(List<ReqIdEntity> ids,@Context ContainerRequestContext crc){
		try{
			log.info(ids.size()+"="+ids);
			int authorId = (int) crc.getProperty(Constant.AUTHOR_ID);
			List<AlumnicTalkPraise> contentComments =talkPraiseDao.getTalkPraisesByIds(ids, authorId);
			return CustomExceptionUtil.toResponse(contentComments);
		}catch(Exception e){
			ExceptionUtil.recodeException(e, log);
			throw e;
		}
	}
	
	@POST
	@Path(PathConstant.ALUMNI_TALK_SUB_PATH_VIEW_GET_COMMENTS)
	public Response getTalkCommentsByIds(List<ReqIdEntity> ids,@Context ContainerRequestContext crc){
		try{
			log.info(ids.size()+"="+ids);
			int authorId = (int) crc.getProperty(Constant.AUTHOR_ID);
			List<AlumniTalkComment> contentComments =talkCommentDao.getTalkCommentsByIds(ids, authorId);
			return CustomExceptionUtil.toResponse(contentComments);
		}catch(Exception e){
			ExceptionUtil.recodeException(e, log);
			throw e;
		}
		
	}
}
