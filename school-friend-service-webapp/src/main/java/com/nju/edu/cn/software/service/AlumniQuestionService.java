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

import com.nju.edu.cn.software.domain.AlumniQuestion;
import com.nju.edu.cn.software.domain.ContentComment;
import com.nju.edu.cn.software.domain.RespAlumniQuestion;
import com.nju.edu.cn.software.entity.LabelInfo;
import com.nju.edu.cn.software.entity.ReqAlumniQuestion;
import com.nju.edu.cn.software.entity.ReqIdEntity;
import com.nju.edu.cn.software.entity.ReqQueryEntity;
import com.nju.edu.cn.software.entity.RespIdEntity;
import com.nju.edu.cn.software.exception.CustomBadRequestException;
import com.nju.edu.cn.software.exception.CustomNotAuthorizedException;
import com.nju.edu.cn.software.helper.Constant;
import com.nju.edu.cn.software.helper.PathConstant;
import com.nju.edu.cn.software.mapper.AlumniQuestionAnswerMapper;
import com.nju.edu.cn.software.mapper.AlumniQuestionMapper;
import com.nju.edu.cn.software.mapper.AuthorMapper;
import com.nju.edu.cn.software.mapper.AuthorizationTokenMapper;
import com.nju.edu.cn.software.mapper.UserDegreeInfoMapper;
import com.nju.edu.cn.software.util.AppUtil;
import com.nju.edu.cn.software.util.CustomExceptionUtil;
import com.nju.edu.cn.software.util.ExceptionLogUtil;
import com.nju.edu.cn.software.util.ExceptionUtil;
import com.nju.edu.cn.software.util.FormDataToken;
import com.nju.edu.cn.software.util.LabelInfoUtil;
import com.nju.edu.cn.software.util.UploadImageUtil;

@Path(PathConstant.ALUMNIS_QUESTION_PATH)
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AlumniQuestionService {

	private static final Logger log = Logger.getLogger(AlumniQuestionService.class);
	
	private static final String FILE = "file";
	private static final String DESCRIPTION = "description";
	private static final String PROBLEM = "problem";
	private static final String WHO_SCAN = "whoScan";
	private static final String LABEL = "label";
	@Autowired
	private AlumniQuestionMapper questionDao;
	@Autowired
	private AlumniQuestionAnswerMapper questionAnswerDao;
	@Autowired
	private AuthorMapper authorDao;
	@Autowired
	private AuthorizationTokenMapper tokenDao;
	@Autowired
	private UserDegreeInfoMapper degreeInfoDao;
	@Context private UriInfo info;
	
	@POST
	@Path(PathConstant.ALUMNIS_QUESTION_SUB_PATH_SAVE)
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public Response  saveAlumniQuestion(FormDataMultiPart multiPart){
		try{
			int authorId = new FormDataToken().tokenValidate(multiPart,tokenDao);
			List<String> labelList =info.getQueryParameters().get("level");
			if(authorId != 0  && labelList!=null){
				String level = labelList.get(0);
				log.info(level);
				List<LabelInfo> labels =LabelInfoUtil.getLabels(degreeInfoDao, level, authorId);
				if(labels.size()!=0){
					AlumniQuestion question = new AlumniQuestion();
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
			        			 final String fileName =builder.toString()+"."+Constant.JPG; 
			        			 images.add(fileName);
			        			 final String path = AppUtil.getAlumniQuestionImagePath();
			            		 InputStream in = bodyPart.getEntityAs(InputStream.class);
			            		 UploadImageUtil.save(path,fileName,in, contentDisposition);
			        		 }
			        		 question.setImages(images);
			        	 } else {
			        		 String filedName = entry.getKey();
			        		 switch(filedName){
			        		 case PROBLEM:
			        			 question.setProblem(entry.getValue().get(0).getValue());
			        			 break;
			        		 case DESCRIPTION:
			        			 question.setDescription(entry.getValue().get(0).getValue());
			        			 break;
			        		 case WHO_SCAN:
			        			 question.setWhoScan(Integer.valueOf(entry.getValue().get(0).getValue()));
			        			 break;
			        		 case LABEL:
			        			 question.setLabel(entry.getValue().get(0).getValue());
			        			 break;
			        		 }
			        	 }
			         }
			         ReqAlumniQuestion reqQuestion = new ReqAlumniQuestion();
			         reqQuestion.setQuestion(question);reqQuestion.setLabelList(labels);
			         questionDao.saveAnswerQuestion(reqQuestion);
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
			ExceptionLogUtil.recordLog(log, e);
			throw e;
		}
		
	}
	
	@POST
	@Path(PathConstant.ALUMNIS_QUESTION_SUB_PATH_CANCEL)
	public Response deleteAlumniQuestion(ReqIdEntity reqEntity,@Context ContainerRequestContext crc){
		try{
			int authorId = (int) crc.getProperty(Constant.AUTHOR_ID);
			questionDao.deleteAnswerQuestion(reqEntity.getId(),authorId);
			return CustomExceptionUtil.toResponse();
		}catch(Exception e){
			ExceptionLogUtil.recordLog(log, e);
			throw e;
		}
	
	}
	
	@POST
	@Path(PathConstant.ALUMNIS_QUESTION_SUB_PATH_CLOSE)
	public Response closeQuestion(RespIdEntity reqEntity,@Context ContainerRequestContext crc){
		try{
			int authorId = (int) crc.getProperty(Constant.AUTHOR_ID);
			questionDao.closeQuestion(reqEntity.getId(),authorId);
			return CustomExceptionUtil.toResponse();
		}catch(Exception e){
			ExceptionLogUtil.recordLog(log, e);
			throw e;
		}
	}
	
	@POST
	@Path(PathConstant.ALUMNIS_QUESTION_SUB_PATH_VIEW)
	public Response viewAlumniQuestions(ReqQueryEntity reqEntity,
			@Context ContainerRequestContext crc,
			@NotNull @QueryParam("level")String level){
		try{
			int authorId = (int) crc.getProperty(Constant.AUTHOR_ID);
			List<LabelInfo> labels =LabelInfoUtil.getLabels(degreeInfoDao, level, authorId);
			if(labels.size()>=1){
				List<RespAlumniQuestion> questions =questionDao.getAlumniQuestions(labels, 
						reqEntity.getRowId(),reqEntity.getLimit(),reqEntity.getDir());
				return CustomExceptionUtil.toResponse(questions);
			}
			else{
				return CustomBadRequestException.toResponseInvalidParams();
			}
		}catch(Exception e){
			ExceptionLogUtil.recordLog(log, e);
			throw e;
		}
		
	}
	
	@POST
	@Path(PathConstant.ALUMNIS_QUESTION_SUB_PATH_GET_ALUMNIQUESTION_BYAUTHORID)
	public Response viewAlumniQuestionsByAuthorId(ReqQueryEntity reqEntity,
			@Context ContainerRequestContext crc){
		try{
			int authorId = (int) crc.getProperty(Constant.AUTHOR_ID);
			List<LabelInfo> labels =LabelInfoUtil.getLabels(degreeInfoDao, Constant.LEVEL_ALL, authorId);
			if(labels.size()>=1){
				List<RespAlumniQuestion> questions =questionDao.getAlumniQuestionsByAuthorId(labels, 
						reqEntity.getRowId(),reqEntity.getLimit(),reqEntity.getDir(),reqEntity.getAuthorId());
				return CustomExceptionUtil.toResponse(questions);
			}
			else{
				return CustomBadRequestException.toResponseInvalidParams();
			}
		}catch(Exception e){
			ExceptionLogUtil.recordLog(log, e);
			throw e;
		}
		
	}
	
	@POST
	@Path(PathConstant.ALUMNIS_QUESTION_SUB_PATH_VIEW_OWN)
	public Response viewOwnAlumniQuestions(ReqQueryEntity reqEntity,
			@Context ContainerRequestContext crc,
			@NotNull @QueryParam("level")String level){
		try{
			int authorId = (int) crc.getProperty(Constant.AUTHOR_ID);
			List<LabelInfo> labels =LabelInfoUtil.getLabels(degreeInfoDao, level, authorId);
			if(labels.size()>=1){
				List<AlumniQuestion> questions =questionDao.viewOwnAlumniQuestions(labels, authorId,
						reqEntity.getRowId(),reqEntity.getLimit(),reqEntity.getDir());
				return CustomExceptionUtil.toResponse(questions);
			}else{
				return CustomBadRequestException.toResponseInvalidParams();
			}
		}catch(Exception e){
			ExceptionLogUtil.recordLog(log, e);
			throw e;
		}
	}
	
	@POST
	@Path(PathConstant.ALUMNIS_QUESTION_SUB_PATH_VIEW_YEAR)
	public Response viewAlumniQuestionsByYear(ReqQueryEntity reqEntity,
			@Context ContainerRequestContext crc,
			@NotNull @QueryParam("level")String level){
		try{
			int authorId = (int) crc.getProperty(Constant.AUTHOR_ID);
			List<LabelInfo> labels =LabelInfoUtil.getLabels(degreeInfoDao, level, authorId);
			if(labels.size()>=1){
				List<RespAlumniQuestion> questions =questionDao.viewAlumniQuestionsByYear(labels,reqEntity.getEntryYear(), 
						reqEntity.getRowId(),reqEntity.getLimit(),reqEntity.getDir());
				return CustomExceptionUtil.toResponse(questions);
			}else{
				return CustomBadRequestException.toResponseInvalidParams();
			}
		}catch(Exception e){
			ExceptionLogUtil.recordLog(log, e);
			throw e;
		}
	}
	
	@POST
	@Path(PathConstant.ALUMNIS_QUESTION_SUB_PATH_VIEW_LABEL)
	public Response viewAlumniQuestionsByLabel(ReqQueryEntity reqEntity,
			@Context ContainerRequestContext crc,
			@NotNull @QueryParam("level")String level){
		try{
			int authorId = (int) crc.getProperty(Constant.AUTHOR_ID);
			List<LabelInfo> labels =LabelInfoUtil.getLabels(degreeInfoDao, level, authorId);
			if(labels.size()>=1){
				List<RespAlumniQuestion> questions =questionDao.viewAlumniQuestionsByLabel(labels,reqEntity.getLabel(), 
						reqEntity.getRowId(),reqEntity.getLimit(),reqEntity.getDir());
				return CustomExceptionUtil.toResponse(questions);
			}else{
				return CustomBadRequestException.toResponseInvalidParams();
			}
		}catch(Exception e){
			ExceptionLogUtil.recordLog(log, e);
			throw e;
		}
	}
	
	@POST
	@Path(PathConstant.ALUMNIS_QUESTION_SUB_PATH_VIEW_COLLEGE)
	public Response viewAlumniQuestionsByCollege(ReqQueryEntity reqEntity,
			@Context ContainerRequestContext crc,
			@NotNull @QueryParam("level")String level){
		ExceptionLogUtil.entryFunction(log, PathConstant.ALUMNIS_QUESTION_SUB_PATH_VIEW_COLLEGE);
		try{
			int authorId = (int) crc.getProperty(Constant.AUTHOR_ID);
			List<LabelInfo> labels =LabelInfoUtil.getLabels(degreeInfoDao, level, authorId);
			if(labels.size()>=1){
				List<RespAlumniQuestion> questions =questionDao.viewAlumniQuestionsByCollege(labels, reqEntity.getCollege(), 
						reqEntity.getRowId(),reqEntity.getLimit(),reqEntity.getDir());
				ExceptionLogUtil.successLeaveFunction(log, PathConstant.ALUMNIS_QUESTION_SUB_PATH_VIEW_COLLEGE);
				return CustomExceptionUtil.toResponse(questions); 
			}
			else{
				log.error("exe fail:labels.size must be >=1");
				return CustomBadRequestException.toResponseInvalidParams();
			}
			
		}catch(Exception e){
			ExceptionLogUtil.errorLeaveFunction(log, PathConstant.ALUMNIS_QUESTION_SUB_PATH_VIEW_COLLEGE);
			ExceptionLogUtil.recordLog(log, e);
			throw e;
		}
		
		
	}
	
	@POST
	@Path(PathConstant.ALUMNIS_QUESTION_SUB_PATH_GET_COMMENTS)
	public Response getQuestionsCommentsByIds(List<ReqIdEntity> ids,@Context ContainerRequestContext crc){
		ExceptionLogUtil.entryFunction(log, PathConstant.ALUMNIS_QUESTION_SUB_PATH_GET_COMMENTS);
		try{
			int authorId = (int) crc.getProperty(Constant.AUTHOR_ID);
			List<ContentComment> contentComments =questionAnswerDao.getQuestionAnswers(ids);
			ExceptionLogUtil.successLeaveFunction(log,PathConstant.ALUMNIS_QUESTION_SUB_PATH_GET_COMMENTS);
			return CustomExceptionUtil.toResponse(contentComments);
		}catch(Exception e){
			ExceptionLogUtil.errorLeaveFunction(log,PathConstant.ALUMNIS_QUESTION_SUB_PATH_GET_COMMENTS);
			ExceptionLogUtil.recordLog(log, e);
			throw e;
		}
	}
	
	@GET
	@Consumes(MediaType.TEXT_PLAIN)
	@Path(PathConstant.ALUMNIS_QUESTION_SUB_PATH_GET_LABELS)
	public Response getLabels(){
		try{
			List<String> labels = questionDao.getLabels();
			return CustomExceptionUtil.toResponse(labels);
		}catch(Exception e){
			ExceptionUtil.recodeException(e, log);
			throw e;
		}
	}
	
}
