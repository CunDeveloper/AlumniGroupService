package com.nju.edu.cn.software.service;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;

import javax.validation.constraints.NotNull;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
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

import com.nju.edu.cn.software.domain.AlumnusVoice;
import com.nju.edu.cn.software.domain.AlumnusVoicePraise;
import com.nju.edu.cn.software.domain.ContentComment;
import com.nju.edu.cn.software.domain.RespAlumnicVoice;
import com.nju.edu.cn.software.entity.AuthorInfo;
import com.nju.edu.cn.software.entity.LabelInfo;
import com.nju.edu.cn.software.entity.ReqAlumnusVoice;
import com.nju.edu.cn.software.entity.ReqIdEntity;
import com.nju.edu.cn.software.entity.ReqQueryEntity;
import com.nju.edu.cn.software.exception.CustomBadRequestException;
import com.nju.edu.cn.software.exception.CustomNotAuthorizedException;
import com.nju.edu.cn.software.helper.Constant;
import com.nju.edu.cn.software.helper.PathConstant;
import com.nju.edu.cn.software.mapper.AlumnsVoiceMapper;
import com.nju.edu.cn.software.mapper.AlumnusVoiceCommentMapper;
import com.nju.edu.cn.software.mapper.AlumnusVoicePraiseMapper;
import com.nju.edu.cn.software.mapper.AuthorizationTokenMapper;
import com.nju.edu.cn.software.mapper.UserDegreeInfoMapper;
import com.nju.edu.cn.software.util.AppUtil;
import com.nju.edu.cn.software.util.CustomExceptionUtil;
import com.nju.edu.cn.software.util.ExceptionUtil;
import com.nju.edu.cn.software.util.FormDataToken;
import com.nju.edu.cn.software.util.LabelInfoUtil;
import com.nju.edu.cn.software.util.UploadImageUtil;

@Path(PathConstant.ALUMNS_VOICE_PATH)
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class AlumnsVoiceService {
	private static final Logger log = Logger.getLogger(AlumnsVoiceService.class);
	
	private static final String FILE = "file";
	private static final String TITLE = "title";
	private static final String CONTENT = "content";
	private static final String WHO_SCAN = "whoScan";
	private static final String LABEL ="label";
	@Autowired
	private AlumnsVoiceMapper alumnsVoiceDao;
	@Autowired
	private AuthorizationTokenMapper tokenDao;
	@Autowired
	private UserDegreeInfoMapper degreeInfoDao;
	@Autowired
	private AlumnusVoicePraiseMapper praiseDao;
	@Autowired
	private AlumnusVoiceCommentMapper commentDao;
	
	@Context private UriInfo info;
	
	@POST
	@Path(PathConstant.ALUMNS_VOICE_SUB_PATH_SAVE)
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public Response saveContent(FormDataMultiPart multiPart){
		try{
			int authorId = new FormDataToken().tokenValidate(multiPart,tokenDao);
			List<String> labelList =info.getQueryParameters().get("level");
			if(authorId != 0 && labelList!=null){
				String level =labelList.get(0);
				List<LabelInfo> labels =LabelInfoUtil.getLabels(degreeInfoDao, level, authorId);
				if(labels.size()!=0){
					AuthorInfo author = new AuthorInfo();author.setAuthorId(authorId);
					ReqAlumnusVoice reqAlumnsVoice = new ReqAlumnusVoice();
					AlumnusVoice alumnsVoice = new AlumnusVoice();
					alumnsVoice.setAuthor(author);
					Map<String,List<FormDataBodyPart>> maps = multiPart.getFields();
					List<String> images = new ArrayList<String>();
			         for(Entry<String,List<FormDataBodyPart>> entry:maps.entrySet()){
			        	 if (entry.getKey().equals(FILE)){
			        		 List<FormDataBodyPart> fileBodyParts =entry.getValue();
			        		 for(FormDataBodyPart bodyPart:fileBodyParts){
			        			 FormDataContentDisposition contentDisposition=bodyPart.getFormDataContentDisposition();
			        			 UUID uuid = UUID.randomUUID();
			        			 StringBuilder builder = new StringBuilder();
			        			 for(String str:(uuid+"").split("-")){
			        				 builder.append(str);
			        	          }
			        			 final String fileName = builder.toString()+"."+Constant.JPG;
			        			 images.add(fileName);
			            		 InputStream in = bodyPart.getEntityAs(InputStream.class);
			            		 UploadImageUtil.save(AppUtil.getAlumnusVoiceImagePath(),fileName,in, contentDisposition);
			        		 }
			        		 alumnsVoice.setImages(images);
			        	 } else {
			        		 String filedName = entry.getKey();
			        		 switch(filedName){
			        		 case TITLE:
			        			 alumnsVoice.setTitle(entry.getValue().get(0).getValue());
			        			 break;
			        		 case CONTENT:
			        			 alumnsVoice.setContent(entry.getValue().get(0).getValue());
			        			 break;
			        		 case WHO_SCAN:
			        			 alumnsVoice.setWhoScan(Integer.valueOf(entry.getValue().get(0).getValue()));
			        			 break;
			        		 case LABEL:
			        			 alumnsVoice.setLabel(entry.getValue().get(0).getValue());
			        			 break;
			        		 }
			        	 }
			         }
			        reqAlumnsVoice.setAlumnusVoice(alumnsVoice);
			        reqAlumnsVoice.setLabelList(labels);
			        alumnsVoiceDao.saveAlumnsVoice(reqAlumnsVoice);
			        return CustomExceptionUtil.toResponse();
				}else{
					return CustomBadRequestException.toResponseInvalidParams();
				}
			}
			else{
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
	@Path(PathConstant.ALUMNS_VOICE_SUB_PATH_CANCEL)
	public Response cancelAlumnsVoice(ReqIdEntity reqEntity,@Context ContainerRequestContext crc){
		try{
			int authorId = (int) crc.getProperty(Constant.AUTHOR_ID);
//			final String imgPath = alumnsVoiceDao.queryImagePath(reqEntity.getId(),authorId);
//			UploadImageUtil.deleteImage(AppUtil.getAlumnusVoiceImagePath(),imgPath);
			alumnsVoiceDao.cancelAlumnsVoice(reqEntity.getId(),authorId);
			return CustomExceptionUtil.toResponse();
		}catch(Exception e){
			ExceptionUtil.recodeException(e, log);
			throw e;
		}
	}
	
	@POST
	@Path(PathConstant.ALUMNS_VOICE_SUB_PATH_VIEW_OWN_VOICE)
	public Response viewOwnVoices(ReqQueryEntity reqEntity,@Context ContainerRequestContext crc
			,@NotNull @QueryParam("level")String level){
		try{
			log.info(level);
			int authorId = (int) crc.getProperty(Constant.AUTHOR_ID);
			List<LabelInfo> labels =LabelInfoUtil.getLabels(degreeInfoDao, level, authorId);
			if(labels.size()>=1){
				List<AlumnusVoice> alumniVoices = alumnsVoiceDao.viewOwnVoices(labels,authorId,
						reqEntity.getRowId(),reqEntity.getLimit(),reqEntity.getDir());
				return CustomExceptionUtil.toResponse(alumniVoices);
			}else{
				return CustomBadRequestException.toResponseInvalidParams();
			}
		}catch(Exception e){
			ExceptionUtil.recodeException(e, log);
			throw e;
		}
	}
	
	@POST
	@Path(PathConstant.ALUMNS_VOICE_SUB_PATH_VIEW_BY_COLLEGE_TYPE)
	public Response getAlumnusVoicesByTypesAndCollege(ReqQueryEntity reqEntity,@Context ContainerRequestContext crc
			,@NotNull @QueryParam("level")String level,
			@NotNull @QueryParam("label")String label){
		try{
			log.info(level);
			int authorId = (int) crc.getProperty(Constant.AUTHOR_ID);
			List<LabelInfo> labels =LabelInfoUtil.getLabels(degreeInfoDao, level, authorId);
			if(labels.size()>=1){
				List<RespAlumnicVoice> alumniVoices = alumnsVoiceDao.getAlumnusVoicesByTypesAndCollege(labels,label,reqEntity.getCollege(),
						reqEntity.getRowId(),reqEntity.getLimit(),reqEntity.getDir());
				return CustomExceptionUtil.toResponse(alumniVoices);
			}else{
				return CustomBadRequestException.toResponseInvalidParams();
			}
		}catch(Exception e){
			ExceptionUtil.recodeException(e, log);
			throw e;
		}
		
	}
	
	 
	
	@POST
	@Path(PathConstant.ALUMNS_VOICE_SUB_PATH_VIEW_BY_TYPE)
	public Response getAlumnusVoicesByTypes(ReqQueryEntity reqEntity,@Context ContainerRequestContext crc
			,@NotNull @QueryParam("level")String level,
			@NotNull @QueryParam("label")String label){
		try{
			log.info(level);
			int authorId = (int) crc.getProperty(Constant.AUTHOR_ID);
			List<LabelInfo> labels =LabelInfoUtil.getLabels(degreeInfoDao, level, authorId);
			if(labels.size()>=1){
				List<RespAlumnicVoice> alumniVoices = alumnsVoiceDao.getAlumnusVoicesByTypes(labels, label,
						reqEntity.getRowId(),reqEntity.getLimit(),reqEntity.getDir());
				return CustomExceptionUtil.toResponse(alumniVoices);
			}else{
				return CustomBadRequestException.toResponseInvalidParams();
			}
		}catch(Exception e){
			ExceptionUtil.recodeException(e, log);
			throw e;
		}
		
	} 
	
	@POST
	@Path(PathConstant.ALUMNS_VOICE_SUB_PATH_VIEW_BY_COLLEGE)
	public Response getAlumnusVoicesByCollege(ReqQueryEntity reqEntity,@Context ContainerRequestContext crc
			,@NotNull @QueryParam("level")String level
			 ){
		try{
			log.info(level);
			int authorId = (int) crc.getProperty(Constant.AUTHOR_ID);
			List<LabelInfo> labels =LabelInfoUtil.getLabels(degreeInfoDao, level, authorId);
			if(labels.size()>=1){
				List<RespAlumnicVoice> alumniVoices = alumnsVoiceDao.getAlumnusVoicesByCollege(labels,reqEntity.getCollege(),
						reqEntity.getRowId(),reqEntity.getLimit(),reqEntity.getDir());
				return CustomExceptionUtil.toResponse(alumniVoices);
			}else{
				return CustomBadRequestException.toResponseInvalidParams();
			}
		}catch(Exception e){
			ExceptionUtil.recodeException(e, log);
			throw e;
		}
		
	} 
 
	@POST
	@Path(PathConstant.ALUMNS_VOICE_SUB_PATH_GET_ALL_VOICE)
	public Response getAllVoices(ReqQueryEntity reqEntity,@Context ContainerRequestContext crc
			,@NotNull @QueryParam("level")String level
			 ){
		try{
			log.info(level);
			int authorId = (int) crc.getProperty(Constant.AUTHOR_ID);
			List<LabelInfo> labels =LabelInfoUtil.getLabels(degreeInfoDao, level, authorId);
			if(labels.size()>=1){
				List<RespAlumnicVoice> alumniVoices = alumnsVoiceDao.getAlumnusVoices (labels,
						reqEntity.getRowId(),reqEntity.getLimit(),reqEntity.getDir());
				return CustomExceptionUtil.toResponse(alumniVoices);
			}else{
				return CustomBadRequestException.toResponseInvalidParams();
			}
		}catch(Exception e){
			ExceptionUtil.recodeException(e, log);
			throw e;
		}
		
	} 
	
	@POST
	@Path(PathConstant.ALUMNS_VOICE_SUB_PATH_GET_VOICE_BYAUTHORID)
	public Response getAllVoicesByAuthorId(ReqQueryEntity reqEntity,@Context ContainerRequestContext crc
			 ){
		try{
			int authorId = (int) crc.getProperty(Constant.AUTHOR_ID);
			List<LabelInfo> labels =LabelInfoUtil.getLabels(degreeInfoDao, Constant.LEVEL_ALL, authorId);
			if(labels.size()>=1){
				List<RespAlumnicVoice> alumniVoices = alumnsVoiceDao.getAlumnusVoicesByAuthorId(labels,
						reqEntity.getRowId(),reqEntity.getLimit(),reqEntity.getDir(),reqEntity.getAuthorId());
				return CustomExceptionUtil.toResponse(alumniVoices);
			}else{
				return CustomBadRequestException.toResponseInvalidParams();
			}
		}catch(Exception e){
			ExceptionUtil.recodeException(e, log);
			throw e;
		}
		
	} 
	
	
	@POST
	@Path(PathConstant.ALUMNS_VOICE_SUB_PATH_VIEW_GET_PRAISE)
	public Response getTalkPraiseByIds(List<ReqIdEntity> ids,@Context ContainerRequestContext crc){
		try{
			log.info(ids.size()+"="+ids);
			int authorId = (int) crc.getProperty(Constant.AUTHOR_ID);
			List<AlumnusVoicePraise> priseComments =praiseDao.getPraisesByVoiceId(ids);
			return CustomExceptionUtil.toResponse(priseComments);
		}catch(Exception e){
			ExceptionUtil.recodeException(e, log);
			throw e;
		}
		
	}
	
	@POST
	@Path(PathConstant.ALUMNS_VOICE_SUB_PATH_VIEW_GET_COMMENT)
	public Response getTalkCommentsByIds(List<ReqIdEntity> ids,@Context ContainerRequestContext crc){
		try{
			log.info(ids.size()+"="+ids);
			int authorId = (int) crc.getProperty(Constant.AUTHOR_ID);
			List<ContentComment> contentComments =commentDao.getCommentsByVoiceId(ids);
			return CustomExceptionUtil.toResponse(contentComments);
		}catch(Exception e){
			ExceptionUtil.recodeException(e, log);
			throw e;
		}
	}
	
	@GET
	@Path(PathConstant.ALUMNS_VOICE_SUB_PATH_GET_LABELS)
	@Consumes(MediaType.TEXT_PLAIN)
	public Response getLabels(){
		try{
			List<String> labels = alumnsVoiceDao.getVoiceLabel();
			return CustomExceptionUtil.toResponse(labels);
		}catch(Exception e){
			ExceptionUtil.recodeException(e, log);
			throw e;
		}
	}
}
