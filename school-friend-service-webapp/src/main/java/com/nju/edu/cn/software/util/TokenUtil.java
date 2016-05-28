package com.nju.edu.cn.software.util;

import java.io.IOException;
import java.util.Random;

import org.apache.log4j.Logger;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.node.ObjectNode;
import com.nju.edu.cn.software.domain.AuthenticationAccessToken;

public class TokenUtil {

	private static final String TOKEN_ID = "tokenId";
	private static final String USER_ID = "userId";
	private static final String DIVICE_ID = "diviceId";
	private static final String APP_ID = "appId";
	private static final Logger logger = Logger.getLogger(TokenUtil.class);
	private static final int TOKEN_THRESHOLD = 500000000;
	 
	public static AuthenticationAccessToken getAccessToken(final String enctyData){
		AuthenticationAccessToken accessToken = null;
		try {
			
			ObjectMapper mapper = new ObjectMapper();
			final String decryData = CyptUtil.dataDecryption(enctyData);
			logger.info(decryData);
			ObjectNode objectNode = (ObjectNode) mapper.readTree(decryData);
			final String tokenId = objectNode.get(TOKEN_ID).asText();
			final String userId =  objectNode.get(USER_ID).asText();
			final String diviceId = objectNode.get(DIVICE_ID).asText();
			final String appId = objectNode.get(APP_ID).asText();
			if(tokenId!=null&&!tokenId.trim().equals("")&&userId!=null&&!userId.trim().equals("")
					&&diviceId!=null&&!diviceId.trim().equals("")
					&&appId!=null&&!appId.trim().equals("")){
				accessToken = new AuthenticationAccessToken();
				accessToken.setTokenId(tokenId);
				accessToken.setDiviceId(diviceId);
				accessToken.setAppId(appId);
				accessToken.setUserId(Integer.valueOf(userId));
			}
			logger.info(appId);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return accessToken;
	}
	
	/**
	 * To Check A Token Whether Is Validation
	 * @param tokenData
	 * @return isValidate
	 */
	public static boolean tokenValidation(String tokenData) {
		boolean isValidate = true;
		String[] value = tokenData.split(":");
		String time = value[0];
		boolean isNumeric = time.matches("\\d+");
		if(!isNumeric){
			isValidate = false; 
			return isValidate;
		}
		
		long startTime = Long.parseLong(time);
		long currentTime = System.currentTimeMillis()/1000;
		 
		if(Math.abs(currentTime - startTime) > Long.valueOf(TOKEN_THRESHOLD)){
			isValidate = false;
			logger.error("_message=\"Error, packet unauthorized. Time difference between start time and current time greater than time threshold."
				+ " Token Data={}, start time={}, current time={}, threshold={}.\"" + tokenData);
			return isValidate;
		}
		return isValidate;
	}
	
 
	/**
	 * Fetch a token which is encrypted using token secret key
	 * @return token
	 * @throws Exception 
	 */
	public static String getTokenId() {
		String time = String.valueOf(System.currentTimeMillis()/1000);
		Random random = new Random();
		String pid = String.valueOf(random.nextInt(65535));
		String data = time + pid;
		return data;
	}
}
