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

import com.nju.edu.cn.software.domain.CommentAuthorInfo;
import com.nju.edu.cn.software.domain.ContentComment;
import com.nju.edu.cn.software.domain.RecommendWork;
import com.nju.edu.cn.software.domain.ReqCommendWork;
import com.nju.edu.cn.software.domain.RespRecommendWork;
import com.nju.edu.cn.software.entity.AuthorInfo;
import com.nju.edu.cn.software.entity.LabelInfo;
import com.nju.edu.cn.software.entity.ReqIdEntity;
import com.nju.edu.cn.software.entity.ReqQueryEntity;
import com.nju.edu.cn.software.exception.CustomBadRequestException;
import com.nju.edu.cn.software.exception.CustomNotAuthorizedException;
import com.nju.edu.cn.software.helper.Constant;
import com.nju.edu.cn.software.helper.PathConstant;
import com.nju.edu.cn.software.mapper.AskRecommendWorkMapper;
import com.nju.edu.cn.software.mapper.AuthorMapper;
import com.nju.edu.cn.software.mapper.AuthorizationTokenMapper;
import com.nju.edu.cn.software.mapper.RecommendWorkMapper;
import com.nju.edu.cn.software.mapper.UserDegreeInfoMapper;
import com.nju.edu.cn.software.util.AppUtil;
import com.nju.edu.cn.software.util.CustomExceptionUtil;
import com.nju.edu.cn.software.util.ExceptionUtil;
import com.nju.edu.cn.software.util.FormDataToken;
import com.nju.edu.cn.software.util.LabelInfoUtil;
import com.nju.edu.cn.software.util.UploadImageUtil;

@Path(PathConstant.RECOMMEND_WORK_PATH)
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class RecommendWorkService {

	private static final String FILE = "file";
	private static final String CONTEXT = "content";
	private static final String TITLE = "title";
	private static final String TYPE = "type";
	private static final String EMAIL = "email";
	private static final Logger log = Logger.getLogger(RecommendWorkService.class);
	@Autowired
	private RecommendWorkMapper recommendWorkDao;
	@Autowired
	private AskRecommendWorkMapper askRecommendWorkDao;
	@Autowired
	private AuthorMapper authorDao;
	@Autowired
	private AuthorizationTokenMapper tokenDao;
	@Autowired
	private UserDegreeInfoMapper degreeInfoDao;
	@Context private UriInfo info;
	
	@POST
	@Path(PathConstant.RECOMMEND_WORK_SUB_PATH_SAVE)
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public Response saveRecommendWork(FormDataMultiPart multiPart){
		try{
			int authorId = new FormDataToken().tokenValidate(multiPart,tokenDao);
			List<String> labelList =info.getQueryParameters().get("level");
			if(authorId != 0 && labelList!=null){
				String level = labelList.get(0);
				List<LabelInfo> labels =LabelInfoUtil.getLabels(degreeInfoDao, level, authorId);
				if(labels.size()!=0){
					RecommendWork recommendWork = new RecommendWork();
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
			        			 final String fileName =builder.toString()+contentDisposition.getFileName(); 
			        			 images.add(fileName);
			        			 final String path = AppUtil.getRecommendWorkImagePath();
			            		 InputStream in = bodyPart.getEntityAs(InputStream.class);
			            		 UploadImageUtil.save(path,fileName,in, contentDisposition);
			        		 }
			        		 recommendWork.setImages(images);
			        	 } else {
			        		 String filedName = entry.getKey();
			        		 switch(filedName){
			        		 case CONTEXT:
			        			 recommendWork.setContent(entry.getValue().get(0).getValue());
			        			 break;
			        		 case TITLE:
			        			 recommendWork.setTitle(entry.getValue().get(0).getValue());
			        			 break;
			        		 case TYPE:
			        			 recommendWork.setType(Integer.valueOf(entry.getValue().get(0).getValue()));
			        			 break;
			        		 case EMAIL:
			        			 recommendWork.setEmail(entry.getValue().get(0).getValue());
			        			 break;
			        		 }
			        	 }
			         }
			        ReqCommendWork reqWork = new ReqCommendWork();
			        reqWork.setLabelList(labels);
			        reqWork.setRecommendWork(recommendWork);
			        recommendWorkDao.saveRecommendWork(reqWork);
			        return CustomExceptionUtil.toResponse();
				}else{
					return CustomBadRequestException.toResponse("参数不合法");
				}
				
			}
			else{
				if(authorId ==0)
					return CustomNotAuthorizedException.toResponse("not authorized");
				return CustomBadRequestException.toResponse("参数不合法");
			}
		}catch(Exception e){
			ExceptionUtil.recodeException(e, log);
			throw e;
		}
	}
	
	@POST
	@Path(PathConstant.RECOMMEND_WORK_SUB_PATH_ASK)
	public Response saveComment(ContentComment contentComment,@Context ContainerRequestContext crc){
		try{
			if(contentComment.getId()!=0&&contentComment.getContentId()!=0||
					contentComment.getId()==0&&contentComment.getContentId()==0){
				return CustomBadRequestException.toResponse("参数不合法,id或者contentId只能有一个");
			}else{
				int authorId = (int) crc.getProperty(Constant.AUTHOR_ID);
				CommentAuthorInfo askedAuthor = queryAuthor(contentComment);
				if(askedAuthor!=null){
					AuthorInfo author = new AuthorInfo();author.setAuthorId(authorId);
					author.setAuthorName(authorDao.getAuthorName(authorId));
					contentComment.setCommentAuthor(author);
					contentComment.setCommentedAuthor(askedAuthor);
					if(askedAuthor.getContentId()!=0){
						contentComment.setContentId(askedAuthor.getContentId());
					}
					recommendWorkDao.contentComment(contentComment);
				}else{
					return CustomBadRequestException.toResponseInvalidParams();
				}
				 return CustomExceptionUtil.toResponse(contentComment.getId());
			}
		}catch(Exception e){
			ExceptionUtil.recodeException(e, log);
			throw e;
		}
	}
	
	private CommentAuthorInfo queryAuthor(ContentComment contentComment){
		if(contentComment.getId()!=0){
			CommentAuthorInfo author = askRecommendWorkDao.getAskAuthorById(contentComment.getId());
			if(author!=null &&null!=author.getAuthorName()&&!author.getAuthorName().trim().equals("")){
				return author;
			}else{
				return null;
			}
		}else if(contentComment.getContentId()!=0){
			CommentAuthorInfo author = recommendWorkDao.queryAuthorById(contentComment.getContentId());
			if(author!=null &&0!=author.getAuthorId()){
				return author;
			}else{
				return null;
			}
		}
		return null;
	}
	
	@POST
	@Path(PathConstant.RECOMMEND_WORK_SUB_PATH_CANCEL)
	public Response cancelRecommendWork(ReqIdEntity reqEntity,@Context ContainerRequestContext crc){
		try{
			int authorId = (int) crc.getProperty(Constant.AUTHOR_ID);
			final String imgPath = recommendWorkDao.queryImagePath(reqEntity.getId(),authorId);
			//UploadImageUtil.deleteImage(AppUtil.getRecommendWorkImagePath(),imgPath);
			recommendWorkDao.deleteRecommendWork(reqEntity.getId(),authorId);
			return CustomExceptionUtil.toResponse(Constant.OK_MSG);
		}catch(Exception e){
			ExceptionUtil.recodeException(e, log);
			throw e;
		}
		
	}
	
	@POST
	@Path(PathConstant.RECOMMEND_WORK_SUB_PATH_QUERY)
	public Response queryRecommendWork(ReqQueryEntity reqEntity,
			@Context ContainerRequestContext crc,
			@NotNull @QueryParam("level")String level){
		try{
			log.info(level);
			int authorId = (int) crc.getProperty(Constant.AUTHOR_ID);
			List<LabelInfo> labels =LabelInfoUtil.getLabels(degreeInfoDao, level, authorId);
			if(labels.size()>=1){
				List<RespRecommendWork> recommendWorks =recommendWorkDao.getRecommendWorks(labels,
						reqEntity.getRowId(),reqEntity.getLimit(),reqEntity.getDir());
				return CustomExceptionUtil.toResponse(recommendWorks);
			}else{
				return CustomBadRequestException.toResponseInvalidParams();
			}
		}catch(Exception e){
			ExceptionUtil.recodeException(e, log);
			throw e;
		}
	}
	
	@POST
	@Path(PathConstant.RECOMMEND_WORK_SUB_PATH_GET_RECOMMEND_BYAUTHORID)
	public Response queryRecommendWorkByAuthorId(ReqQueryEntity reqEntity,
			@Context ContainerRequestContext crc){
		try{
			int authorId = (int) crc.getProperty(Constant.AUTHOR_ID);
			List<LabelInfo> labels =LabelInfoUtil.getLabels(degreeInfoDao,Constant.LEVEL_ALL, authorId);
			if(labels.size()>=1){
				List<RespRecommendWork> recommendWorks =recommendWorkDao.getRecommendWorksByAuthorId(labels,
						reqEntity.getRowId(),reqEntity.getLimit(),reqEntity.getDir(),reqEntity.getAuthorId());
				return CustomExceptionUtil.toResponse(recommendWorks);
			}else{
				return CustomBadRequestException.toResponseInvalidParams();
			}
		}catch(Exception e){
			ExceptionUtil.recodeException(e, log);
			throw e;
		}
	}
	
	@POST
	@Path(PathConstant.RECOMMEND_WORK_SUB_PATH_VIEW_OWN)
	public Response viewOwnRecommendWorks(ReqQueryEntity reqEntity,
			@Context ContainerRequestContext crc,
			@NotNull @QueryParam("level")String level){
		try{
			int authorId = (int) crc.getProperty(Constant.AUTHOR_ID);
			List<LabelInfo> labels =LabelInfoUtil.getLabels(degreeInfoDao, level, authorId);
			if(labels.size()>=1){
				List<RespRecommendWork> recommendWorks =recommendWorkDao.viewOwnRecommendWorks(labels,authorId,
						reqEntity.getRowId(),reqEntity.getLimit(),reqEntity.getDir());
				return CustomExceptionUtil.toResponse(recommendWorks);
			}else{
				return CustomBadRequestException.toResponseInvalidParams();
			}
		}catch(Exception e){
			ExceptionUtil.recodeException(e, log);
			throw e;
		}
	}
	
	@POST
	@Path(PathConstant.RECOMMEND_WORK_SUB_PATH_BY_TYPE)
	public Response viewRecommendWorksByType(ReqQueryEntity reqEntity,
			@Context ContainerRequestContext crc,
			@NotNull @QueryParam("level")String level){
		try{
			int authorId = (int) crc.getProperty(Constant.AUTHOR_ID);
			List<LabelInfo> labels =LabelInfoUtil.getLabels(degreeInfoDao, level, authorId);
			if(labels.size()>=1){
				List<RespRecommendWork> recommendWorks =recommendWorkDao.viewRecommendWorksByType(labels,reqEntity.getType(),
						reqEntity.getRowId(),reqEntity.getLimit(),reqEntity.getDir());
				return CustomExceptionUtil.toResponse(recommendWorks);
			}else{
				return CustomBadRequestException.toResponseInvalidParams();
			}
		}catch(Exception e){
			ExceptionUtil.recodeException(e, log);
			throw e;
		}
		
		
	}
	
	@POST
	@Path(PathConstant.RECOMMEND_WORK_SUB_PATH_BY_COLLEGE)
	public Response viewRecommendWorksByCollege(ReqQueryEntity reqEntity,
			@Context ContainerRequestContext crc,
			@NotNull @QueryParam("level")String level){
		try{
			int authorId = (int) crc.getProperty(Constant.AUTHOR_ID);
			log.info("college:"+reqEntity.getCollege());
			List<LabelInfo> labels =LabelInfoUtil.getLabels(degreeInfoDao, level, authorId);
			if(labels.size()>=1){
				List<RespRecommendWork> recommendWorks =recommendWorkDao.viewRecommendWorksByCollege(labels,reqEntity.getCollege(),
						reqEntity.getRowId(),reqEntity.getLimit(),reqEntity.getDir());
				return CustomExceptionUtil.toResponse(recommendWorks);
			}else{
				return CustomBadRequestException.toResponseInvalidParams();
			}
		}catch(Exception e){
			ExceptionUtil.recodeException(e, log);
			throw e;
		}
	}
	
	@POST
	@Path(PathConstant.RECOMMEND_WORK_SUB_PATH_BY_TYPE_COLLEGE)
	public Response viewRecommendWorksByTypeAndCollege(ReqQueryEntity reqEntity,
			@Context ContainerRequestContext crc,
			@NotNull @QueryParam("level")String level){
		try{
			int authorId = (int) crc.getProperty(Constant.AUTHOR_ID);
			List<LabelInfo> labels =LabelInfoUtil.getLabels(degreeInfoDao, level, authorId);
			if(labels.size()>=1){
				List<RespRecommendWork> recommendWorks =recommendWorkDao.viewRecommendWorksByTypeAndCollege(labels,reqEntity.getType(),reqEntity.getCollege(),
						reqEntity.getRowId(),reqEntity.getLimit(),reqEntity.getDir());
				return CustomExceptionUtil.toResponse(recommendWorks);
			}else{
				return CustomBadRequestException.toResponseInvalidParams();
			}
		}catch(Exception e){
			ExceptionUtil.recodeException(e, log);
			throw e;
		}
		
	}
	
	@POST
	@Path(PathConstant.RECOMMED_WORK_SUB_PATH_GET_ASKS)
	public Response getRecommendAsksByIds(List<ReqIdEntity> ids){
		try{
			log.info(ids.size()+"="+ids);
			List<ContentComment> contentComments =askRecommendWorkDao.getAskAboutRecommends(ids);
			return CustomExceptionUtil.toResponse(contentComments);
		}catch(Exception e){
			ExceptionUtil.recodeException(e, log);
			throw e;
		}
		
	}
	
}
