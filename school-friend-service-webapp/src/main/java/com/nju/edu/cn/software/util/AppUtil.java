package com.nju.edu.cn.software.util;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;

public class AppUtil {

	private static final Logger log = Logger.getLogger(AppUtil.class);
	
	private static final String FILE_NAME = "app.properties";
	private static final String APP_ID = "appId";
	private static final String DEFAULT_APP_ID = "lMj5Zlkh54lu08fgslAS4lkGWAF3lkky";
	private static final String ALUMNUS_VOICE_IMAGE_KEY = "alumnusVoiceImagePath";
	private static final String RECOMMEND_WORK_IMAGE_KEY = "recommendWorkImagePath";
	private static final String ALUMNI_QUESTION_IMAGE_KEY = "alumniQuestionImgPath";
	private static final String ALUMNI_TALK_KEY = "alumniTalkImagePath";
	private static final String HEAD_ICON_KEY = "headIconImgPath";
	private static final String COLLECT_IMG_KEY = "collectImgPath";
	private static final String BG_IMG_KEY = "bgImgPath";
	private static final String BASE_IMAGE_PATH ="baseImagePath";
	private static Properties prop = null;
	private static String alumnus_voice_image_path = null;
	private static String recommend_work_image_path = null;
	private static String alumni_talk_image_path = null;
	private static String alumni_question_image_path = null;
	private static String base_image_path = null;
	private static String head_icon_image_path = null;
	private static String bg_img_path = null;
	private static String collect_img_path = null;
	
	static{
		prop = new Properties();
		try {
			InputStream in = AppUtil.class.getClassLoader().getResourceAsStream(FILE_NAME);
			prop.load(in);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			ExceptionUtil.recodeException(e, log);
		}
	}
	
	public static String getAppId(){
		if(prop.containsKey(APP_ID))
			return prop.getProperty(APP_ID);
		else
			return DEFAULT_APP_ID;
	}
	
	public static String getAlumnusVoiceImagePath(){
		if (alumnus_voice_image_path == null){
			alumnus_voice_image_path = prop.getProperty(ALUMNUS_VOICE_IMAGE_KEY);
		}
		return getBaseImagePath()+alumnus_voice_image_path;
	}
	
	public static String getRecommendWorkImagePath(){
		if (recommend_work_image_path == null){
			recommend_work_image_path = prop.getProperty(RECOMMEND_WORK_IMAGE_KEY);
		}
		return getBaseImagePath()+recommend_work_image_path;
	}
	
	public static String getAlumniTalkImagePath(){
		if (alumni_talk_image_path == null){
			alumni_talk_image_path = prop.getProperty(ALUMNI_TALK_KEY);
		}
		return getBaseImagePath()+alumni_talk_image_path;
	}
	
	public static String getBaseImagePath(){
		if (base_image_path == null){
			base_image_path = prop.getProperty(BASE_IMAGE_PATH);
		}
		return base_image_path;
	}
	
	public static String getAlumniQuestionImagePath(){
		if (alumni_question_image_path == null){
			alumni_question_image_path = prop.getProperty(ALUMNI_QUESTION_IMAGE_KEY);
		}
		return getBaseImagePath()+alumni_question_image_path;
	}
	
	public static String getHeadIconImagePath(){
		if (head_icon_image_path == null){
			head_icon_image_path = prop.getProperty(HEAD_ICON_KEY);
		}
		return getBaseImagePath()+head_icon_image_path;
	}
	
	public static String getCollectImagePath(){
		if (collect_img_path == null){
			collect_img_path = prop.getProperty(COLLECT_IMG_KEY);
		}
		return getBaseImagePath()+collect_img_path;
	}
	
	public static String getBgImagePath(){
		if (bg_img_path == null){
			bg_img_path = prop.getProperty(BG_IMG_KEY);
		}
		return getBaseImagePath()+bg_img_path;
	}
	
	public static boolean updateAppId(final String appId){
		if(prop.containsKey(APP_ID)){
			prop.setProperty(APP_ID,appId);
			try {
				prop.store(new FileOutputStream(FILE_NAME),APP_ID);
				return true;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				ExceptionUtil.recodeException(e, log);
				return false;
			}
			
		} else
			return false;
	}
}
