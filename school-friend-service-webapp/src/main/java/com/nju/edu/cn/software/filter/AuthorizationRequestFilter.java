package com.nju.edu.cn.software.filter;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.annotation.Priority;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Context;
import javax.ws.rs.ext.Provider;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonParser.Feature;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;

import com.nju.edu.cn.software.domain.AuthenticationAccessToken;
import com.nju.edu.cn.software.entity.LabelInfo;
import com.nju.edu.cn.software.exception.CustomNotAuthorizedException;
import com.nju.edu.cn.software.helper.Constant;
import com.nju.edu.cn.software.helper.PathConstant;
import com.nju.edu.cn.software.mapper.AuthorizationTokenMapper;
import com.nju.edu.cn.software.mapper.UserDegreeInfoMapper;
import com.nju.edu.cn.software.util.ExceptionLogUtil;
import com.nju.edu.cn.software.util.SchoolFriendGson;
import com.nju.edu.cn.software.util.TokenUtil;
 
 

@Priority(Priorities.AUTHENTICATION)
@Provider
public class AuthorizationRequestFilter implements ContainerRequestFilter {
	
	private static final Logger logger = Logger.getLogger(AuthorizationRequestFilter.class);
 
	@Autowired
	private AuthorizationTokenMapper tokenDao;
	@Autowired
	private UserDegreeInfoMapper degreeInfoDao;
	@Context
	private HttpServletRequest servletRequest;
	
	
	@Override
	public void filter(ContainerRequestContext request) throws IOException {
		
		final String IP = servletRequest.getRemoteAddr();
		logger.info(IP+":content type "+request.getMediaType().toString()+"comme in");
		try{
			String url = request.getUriInfo().getAbsolutePath().toString();
			String[] strs = url.split("/");
			String path = strs[strs.length-1];
			logger.info(path);
			if(isJson(request) && !path.equals(PathConstant.AUTHOR_SUB_PATH_REGISTER)
					&&!path.equals(PathConstant.AUTHORIZATION_SUB_PATH_SAVE)
					&&!path.equals(PathConstant.AUTHOR_SUB_PATH_LOGIN)){
//				byte[] bytes = IOUtils.toByteArray(request.getEntityStream());
//				String body = new String(bytes,"UTF-8");
//				logger.info(body);
				logger.info(IP+":request content-type application/json");
				String body = IOUtils.toString(request.getEntityStream(),"UTF-8");
				logger.info(IP+":body "+body);
				ObjectMapper mapper = new ObjectMapper();
				mapper.configure(Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
				mapper.configure(Feature.ALLOW_UNQUOTED_CONTROL_CHARS,true);
				mapper.configure(Feature.ALLOW_BACKSLASH_ESCAPING_ANY_CHARACTER,true);
				mapper.configure(Feature.AUTO_CLOSE_SOURCE,true);
				ObjectNode rootNode = (ObjectNode) mapper.readTree(body);
				JsonNode authNode = rootNode.get(Constant.AUTHORIZATION);
				AuthenticationAccessToken accessToken = TokenUtil.getAccessToken(authNode.asText());
				logger.info(SchoolFriendGson.newInstance().toJson(accessToken)+"json");
				if(accessToken != null) {
					logger.info(IP+":prase authorization success");
					int id = tokenDao.validateAuthorizationToken(accessToken.getTokenId(),
							accessToken.getUserId(),accessToken.getDiviceId(), accessToken.getAppId());
				 
					if(id==1){
						logger.info(IP+":authorization success");
						final int authorId = accessToken.getUserId();
						List<LabelInfo> labels= degreeInfoDao.getLabelInfosByAuthorId(authorId);
						JsonNode bodyNode =  rootNode.get(Constant.BODY);
						String result = bodyNode.toString();
						logger.info(IP+":body content"+result);
						InputStream in = IOUtils.toInputStream(result,"UTF-8");
						
						request.setEntityStream(in);
						request.setProperty(Constant.LABELS, labels);
						request.setProperty(Constant.AUTHOR_ID,authorId);
					   } else{
						   logger.error(IP+":authorization error");
						   request.abortWith(CustomNotAuthorizedException.toResponse(""));
					   }
				} else{
					logger.error(IP+":prase authorizaton error");
					request.abortWith(CustomNotAuthorizedException.toResponse(""));
				}
			logger.info(IP+":filter exe ok");
			} else if(path.equals(PathConstant.AUTHOR_SUB_PATH_REGISTER)
					|| path.equals(PathConstant.AUTHORIZATION_SUB_PATH_SAVE)
					||path.equals(PathConstant.AUTHOR_SUB_PATH_LOGIN)){
				String body = IOUtils.toString(request.getEntityStream());
				logger.info(IP+":body "+body);
				ObjectMapper mapper = new ObjectMapper();
				ObjectNode rootNode = (ObjectNode) mapper.readTree(body);
				JsonNode bodyNode =  rootNode.get(Constant.BODY);
				String result = bodyNode.toString();
				logger.info(IP+":body content"+result);
				InputStream in = IOUtils.toInputStream(result );
				request.setEntityStream(in);
			}
		
		}catch(Exception e){
			logger.error(IP+":filter exe error");
			ExceptionLogUtil.recordLog(logger,e);
			throw e;
		}
	}
	
	private boolean isJson(ContainerRequestContext request) {
        return request.getMediaType().toString().contains(Constant.CONTENT_TYPE_JSON);
    }
	
	@SuppressWarnings("unused")
	private boolean isMultiFormData(ContainerRequestContext request) {
		return request.getMediaType().toString().contains(Constant.MULTIPART_FORM_DATA);
	}
}
