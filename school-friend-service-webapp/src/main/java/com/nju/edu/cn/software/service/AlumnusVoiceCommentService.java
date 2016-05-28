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

import com.nju.edu.cn.software.domain.CommentAuthorInfo;
import com.nju.edu.cn.software.domain.ContentComment;
import com.nju.edu.cn.software.entity.AuthorInfo;
import com.nju.edu.cn.software.entity.ReqIdEntity;
import com.nju.edu.cn.software.exception.CustomBadRequestException;
import com.nju.edu.cn.software.helper.Constant;
import com.nju.edu.cn.software.helper.PathConstant;
import com.nju.edu.cn.software.mapper.AlumnsVoiceMapper;
import com.nju.edu.cn.software.mapper.AlumnusVoiceCommentMapper;
import com.nju.edu.cn.software.mapper.AuthorMapper;
import com.nju.edu.cn.software.util.CustomExceptionUtil;
import com.nju.edu.cn.software.util.ExceptionUtil;

@Path(PathConstant.ALUMNUS_VOICE_COMMENT_PATH)
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON )
public class AlumnusVoiceCommentService {

	private static final Logger log = Logger.getLogger(AlumnusVoiceCommentService.class);
	@Autowired
	private AlumnsVoiceMapper alumnsVoiceDao;
	@Autowired
	private AlumnusVoiceCommentMapper alumnsVoiceCommentDao;
	@Autowired
	private AuthorMapper authorDao;
	
	@POST
	@Path(PathConstant.ALUMNUS_VOICE_COMMENT_SUB_PATH_SAVE)
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
					alumnsVoiceCommentDao.contentComment(contentComment);
					
				}else{
					return CustomBadRequestException.toResponse("参数不合法 ");
				}
				return CustomExceptionUtil.toResponse(contentComment.getId()+"");
			}
		}catch(Exception e){
			ExceptionUtil.recodeException(e, log);
			throw e;
		}
		
		
	}
	
	private CommentAuthorInfo queryAuthor(ContentComment contentComment){
		if(contentComment.getId()!=0){
			CommentAuthorInfo author = alumnsVoiceCommentDao.getCommentAuthorById(contentComment.getId());
			if(author!=null &&null!=author.getAuthorName()&&!author.getAuthorName().trim().equals("")){
				return author;
			}else{
				return null;
			}
		}else if(contentComment.getContentId()!=0){
			CommentAuthorInfo author = alumnsVoiceDao.queryAuthorById(contentComment.getContentId());
			if(author!=null &&0!=author.getAuthorId()){
				return author;
			}else{
				return null;
			}
		}
		return null;
	}
	
	@POST
	@Path(PathConstant.ALUMNUS_VOICE_COMMENT_SUB_PATH_CANCEL)
	public Response cancelAlumnusVoiceComment(ReqIdEntity reqEntity,@Context ContainerRequestContext crc){
		try{
			int authorId = (int) crc.getProperty(Constant.AUTHOR_ID);
			alumnsVoiceCommentDao.cancelAlumnusVoiceComment(reqEntity.getId(),authorId);
			return CustomExceptionUtil.toResponse();
		}catch(Exception e){
			ExceptionUtil.recodeException(e, log);
			throw e;
		}
		
	}
	
}
