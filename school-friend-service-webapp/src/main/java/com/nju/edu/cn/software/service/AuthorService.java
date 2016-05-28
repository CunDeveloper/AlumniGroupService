package com.nju.edu.cn.software.service;

import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.Map.Entry;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
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

import com.nju.edu.cn.software.domain.AuthenticationAccessToken;
import com.nju.edu.cn.software.domain.Author;
import com.nju.edu.cn.software.domain.AuthorImage;
import com.nju.edu.cn.software.exception.CustomBadRequestException;
import com.nju.edu.cn.software.helper.Constant;
import com.nju.edu.cn.software.helper.PathConstant;
import com.nju.edu.cn.software.mapper.AuthorMapper;
import com.nju.edu.cn.software.mapper.AuthorizationTokenMapper;
import com.nju.edu.cn.software.util.AppUtil;
import com.nju.edu.cn.software.util.CustomExceptionUtil;
import com.nju.edu.cn.software.util.ExceptionLogUtil;
import com.nju.edu.cn.software.util.ExceptionUtil;
import com.nju.edu.cn.software.util.FormDataToken;
import com.nju.edu.cn.software.util.MD5Util;
import com.nju.edu.cn.software.util.SchoolFriendGson;
import com.nju.edu.cn.software.util.TokenUtil;
import com.nju.edu.cn.software.util.UploadImageUtil;

 

@Path(PathConstant.AUTHOR_PATH)
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AuthorService {

	private static final Logger log = Logger.getLogger(AuthorService.class);
	private static final String ID = "id";
	private static final String USERNAME = "username";
	private static final String PASSWORD = "password";
	private static SchoolFriendGson gson = SchoolFriendGson.newInstance();
	
	@Autowired
	private AuthorMapper authorDao;
	@Autowired
	private AuthorizationTokenMapper authTokenDao;
	@Context private UriInfo info;
	
	@POST
	@Path(PathConstant.AUTHOR_SUB_PATH_REGISTER)
	public Response saveAuthor(Author author){
		
		List<String> diviceIds= info.getQueryParameters().get("diviceId");
		if(diviceIds != null){
			String diviceId = diviceIds.get(0);
			try{
				String password = author.getPassword();
				//FIVE TIMES MDF ENCPTY
				String finalPass = md5Encypt(password);
				author.setPassword(finalPass);
				authorDao.saveAuthor(author);
				if(author.getId() != 0){
					AuthenticationAccessToken token = new AuthenticationAccessToken();
					token.setUserId(author.getId());
					token.setTokenId(TokenUtil.getTokenId());
					token.setAppId(AppUtil.getAppId());
					token.setDiviceId(diviceId);
					authTokenDao.saveAuthorizationToken(token);
					AuthenticationAccessToken accessToken = authTokenDao.getAuthorizationToken(author.getId());
					return CustomExceptionUtil.toResponse(accessToken);
				}
				return CustomBadRequestException.toResponseInvalidParams(); 
				
			}catch(Exception e){
				ExceptionUtil.recodeException(e, log);
				throw e;
			}
		}else{
			return CustomBadRequestException.toResponseInvalidParams(); 
		}
	}
	
	@POST
	@Path(PathConstant.AUTHOR_SUB_PATH_GET_AUTHOR_BY_ID)
	public Response getAuthorById(int id){
		try{
			Author author = authorDao.getAuthorById(id);
			return CustomExceptionUtil.toResponse(gson.toJson(author));
		}catch(Exception e){
			ExceptionUtil.recodeException(e, log);
			throw e;
		}
		
	}
	
	@POST
	@Path(PathConstant.AUTHOR_SUB_PATH_LOGIN)
	public Response login(Author author){
		List<String> diviceIds= info.getQueryParameters().get("diviceId");
		if(diviceIds != null){
			String diviceId = diviceIds.get(0);
			log.info(diviceId);
			try{
				String password = md5Encypt(author.getPassword());
				log.info(password);
				log.info(author.getLoginName()+"=="+author.getPassword());
				Author aAuth = authorDao.getAuthorByNameAndPass(author.getLoginName(), password);
				 
				if(aAuth != null && aAuth.getId() != 0){
 					String tokenId = TokenUtil.getTokenId();
 					log.info(tokenId);
 					log.info(aAuth.getId());
 					int userId = aAuth.getId();
				    authTokenDao.updateToken(tokenId,diviceId,userId);
				    AuthenticationAccessToken token = authTokenDao.getAuthorizationToken(userId);
				    return CustomExceptionUtil.toResponse(token);
				 } else{
					CustomExceptionUtil.toResponse(Constant.USERNAME_OR_PASS_ERROR);
				 }
			}catch(Exception e){
				ExceptionUtil.recodeException(e, log);
				throw e;
			}
		}else{
			return CustomBadRequestException.toResponseInvalidParams();
		}
		return null;
	}
	
//	@POST
//	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
//	@Path(PathConstant.AUTHOR_SUB_PATH_GET_AUTHOR_BY_USER_PASS)
//	public Response getAuthorByUserPass(@FormParam(USERNAME)String username,@FormParam(PASSWORD)
//	   String password){
//		try{
//			AuthenticationAccessToken accessToken = new AuthenticationAccessToken();
//			int id = authorDao.getAuthorByNameAndPass(username, password);
//			String tokenId = TokenUtil.getTokenId();
//			authTokenDao.updateAuthorizationToken(tokenId, id);
//			accessToken.setUserId(id);
//			accessToken.setUserName(username);
//			accessToken.setTokenId(tokenId);
//			return CustomExceptionUtil.toResponse(gson.toJson(accessToken));
//		}catch(Exception e){
//			ExceptionUtil.recodeException(e, log);
//			throw e;
//		}
//		
//	}
	
	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Path(PathConstant.AUTHOR_SUB_PATH_VALIDATE_USERNAME)
	public Response validateUsernameIsRegister(@FormParam(USERNAME)String username){
		try{
			int id = authorDao.validateUsernameIsRegister(username);
			log.info("id="+id);
			return CustomExceptionUtil.toResponse(id);
		}catch(Exception e){
			ExceptionUtil.recodeException(e, log);
			throw e;
		}
		
	}
	
	@POST
	@Path(PathConstant.AUTHOR_SUB_PATH_UPDATE_HEAD_ICON)
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public Response updateHeadIcon(FormDataMultiPart multiPart){
		try{
			int authorId = new FormDataToken().tokenValidate(multiPart,authTokenDao);
			String fileName = "";
			Map<String,List<FormDataBodyPart>> maps = multiPart.getFields();
			for(Entry<String,List<FormDataBodyPart>> entry:maps.entrySet()){
	        	 if (entry.getKey().equals(Constant.FILE)){
	        		 List<FormDataBodyPart> fileBodyParts =entry.getValue();
	        		 for(FormDataBodyPart bodyPart:fileBodyParts){
	        			 FormDataContentDisposition contentDisposition=bodyPart.getFormDataContentDisposition();
	        			 UUID uuid = UUID.randomUUID();
	        			 StringBuilder builder = new StringBuilder();
	        			 for(String str:(uuid+"").split("-")){
	        				 builder.append(str);
	        	          }
	        			 fileName =builder.toString()+"."+Constant.JPG; 
	        			 final String path = AppUtil.getHeadIconImagePath();
	            		 InputStream in = bodyPart.getEntityAs(InputStream.class);
	            		 UploadImageUtil.save(path,fileName,in, contentDisposition);
	        	      } 
			      }
			}
			if (!fileName.equals("")){
				authorDao.updateHeadIcon(authorId, fileName);
			}
			return CustomExceptionUtil.toResponse();
		}catch(Exception e){
			ExceptionUtil.recodeException(e, log);
			throw e;
		}
		
	}
	
	@POST
	@Path(PathConstant.AUTHOR_SUB_PATH_UPDATE_BG_IMG)
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public Response updateBgImg(FormDataMultiPart multiPart){
		try{
			int authorId = new FormDataToken().tokenValidate(multiPart,authTokenDao);
			String fileName = "";
			Map<String,List<FormDataBodyPart>> maps = multiPart.getFields();
			for(Entry<String,List<FormDataBodyPart>> entry:maps.entrySet()){
	        	 if (entry.getKey().equals(Constant.FILE)){
	        		 List<FormDataBodyPart> fileBodyParts =entry.getValue();
	        		 for(FormDataBodyPart bodyPart:fileBodyParts){
	        			 FormDataContentDisposition contentDisposition=bodyPart.getFormDataContentDisposition();
	        			 UUID uuid = UUID.randomUUID();
	        			 StringBuilder builder = new StringBuilder();
	        			 for(String str:(uuid+"").split("-")){
	        				 builder.append(str);
	        	          }
	        			 fileName =builder.toString()+"."+Constant.JPG; 
	        			 final String path = AppUtil.getBgImagePath();
	            		 InputStream in = bodyPart.getEntityAs(InputStream.class);
	            		 UploadImageUtil.save(path,fileName,in, contentDisposition);
	        	      } 
			      }
			}
			if (!fileName.equals("")){
				authorDao.updateBgImg(authorId,fileName);
			}
			return CustomExceptionUtil.toResponse();
		}catch(Exception e){
			ExceptionUtil.recodeException(e, log);
			throw e;
		}
		
	}
	
	@POST
	@Path(PathConstant.AUTHOR_SUB_PATH_GET_AUTHOR_IMAGE)
	public Response getAuthorImage(@Context ContainerRequestContext crc){
		try{
			int authorId = (int) crc.getProperty(Constant.AUTHOR_ID);
			AuthorImage authorImage = authorDao.getAuthorImage(authorId);
			return CustomExceptionUtil.toResponse(authorImage);
		}catch(Exception e){
			ExceptionUtil.recodeException(e, log);
			throw e;
		}
		
	}
	
	@POST
	@Path(PathConstant.AUTHOR_SUB_PATH_UPDATE_USERNAME)
	public Response updateUserName(Author author,@Context ContainerRequestContext crc){
		ExceptionLogUtil.entryFunction(log, PathConstant.AUTHOR_SUB_PATH_VALIDATE_USERNAME);
		try{
			int authorId = (int) crc.getProperty(Constant.AUTHOR_ID);
			authorDao.updateUserName(author.getUsername(), authorId);
			ExceptionLogUtil.successLeaveFunction(log,PathConstant.ALUMNIS_QUESTION_SUB_PATH_GET_COMMENTS);
			return CustomExceptionUtil.toResponse(Constant.OK_MSG);
		}catch(Exception e){
			ExceptionLogUtil.errorLeaveFunction(log,PathConstant.ALUMNIS_QUESTION_SUB_PATH_GET_COMMENTS);
			ExceptionLogUtil.recordLog(log, e);
			throw e;
		}
	}
	
	//five times md5 encypt
	private String md5Encypt(String input){
		String tempStr = input;
		for(int i=0;i<5;i++){
			tempStr = MD5Util.getMD5(tempStr);
		}
		return tempStr;
	}
}
