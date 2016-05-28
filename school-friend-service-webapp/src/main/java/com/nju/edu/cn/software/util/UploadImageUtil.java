package com.nju.edu.cn.software.util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashSet;
import java.util.Set;

import javax.imageio.ImageIO;

import org.apache.log4j.Logger;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;

import com.nju.edu.cn.software.helper.Constant;

import net.coobird.thumbnailator.Thumbnails;

public class UploadImageUtil {

	private static final Logger log = Logger.getLogger(UploadImageUtil.class);
	private static final int WIDTH = 100;
	private static final String SMALL_IMG_DIR = "small";
	private static final long MAX_SIZE =2097152;
	private static Set<String> imgTypes;
	static{
		imgTypes = new HashSet<>();
		imgTypes.add("png");
		imgTypes.add("jpeg");
		imgTypes.add("gif");
		imgTypes.add("img");
		imgTypes.add("jpe");
		imgTypes.add("bmp");
		imgTypes.add("pgm");
		imgTypes.add("jpg");
		
	}
	
	public static void save(final String desPath,final String fileName,final InputStream in,FormDataContentDisposition filedetail){
		File dir = new File(desPath);
		if(!dir.exists()){
			dir.mkdirs();
		}
		File file = new File(dir,fileName);
		try {
			file.createNewFile();
			BufferedImage img = ImageIO.read(in);
			ImageIO.write(img,Constant.JPG,file);
			img.flush();
			saveSmallImage(img,desPath,fileName);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.error("upload file occur error");
		}
	}
	
	private static void saveSmallImage(BufferedImage img,String dir,String fileName){
		int width = img.getWidth();
		int height =img.getHeight();
		int ratio = 1;
		log.info("width:"+width);
		log.info("height:"+height);
		File fileDir = new File(dir+File.separator+SMALL_IMG_DIR);
		if(!fileDir.exists())
			fileDir.mkdirs();
		File file = new File(fileDir,fileName);
		try {
			file.createNewFile();
			if(width>WIDTH){
				ratio = width/WIDTH;
				height = height/ratio;
			}
			Thumbnails.of(img).size(WIDTH, height).outputFormat(Constant.JPG)
			.toFile(file);
			img.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			log.error("save small image occur error:"+dir+File.separator+fileName);
			e.printStackTrace();
		}
		
	}
	
	public static void deleteImage(final String dirPath,final String fileNames){
		if(fileNames!=null&&!fileNames.trim().equals("")){
			String[] paths = fileNames.split(",");
			File file;
			File smallFile;
			for(String path:paths){
				file = new File(dirPath,path);
				smallFile = new File(dirPath+File.separator+SMALL_IMG_DIR,path);
				log.info("delete "+dirPath+" img:"+path);
				if(file.exists()){
					file.delete();
				}
				if(smallFile.exists()){
					smallFile.delete();
				}
			}
		}
	}
	
	public static boolean isRightImage(final String uploadFileName,final long size){
		log.info("img type:"+uploadFileName);
		log.info("img size:"+size);
		String imgFom = imgFormat(uploadFileName);
		if(imgFom!=null &&imgTypes.contains(imgFom.toLowerCase())&&size<MAX_SIZE)
			return true; 
		return false;
	 
	}
	
	public static String imgFormat(final String fileName){
		if(fileName.indexOf(".")!=-1){
			String[] str = fileName.split("\\.");
			return str[1].trim();
		}
		return null;
	}
	
	public static String imgName(final String fileName){
		if(fileName.indexOf(".")!=-1){
			String[] str = fileName.split("\\.");
			return str[0].trim();
		}
		return null;
	}
	
	public static void main(String args[]){
		 
	}
		
}
