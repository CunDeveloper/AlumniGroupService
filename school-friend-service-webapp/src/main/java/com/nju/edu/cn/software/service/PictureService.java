package com.nju.edu.cn.software.service;

import java.io.File;

import javax.activation.MimetypesFileTypeMap;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.log4j.Logger;

import com.nju.edu.cn.software.helper.PathConstant;
import com.nju.edu.cn.software.util.AppUtil;
import com.nju.edu.cn.software.util.ExceptionUtil;
 
@Produces("image/*")
@Path(PathConstant.IMAGES)
public class PictureService {

	private static final Logger log = Logger.getLogger(PictureService.class);
	@GET
	@Path("{subPath}/{imageName}")
	public Response getImage(@PathParam("subPath") String subPath,@PathParam("imageName") String imageName){
		try{
			final String fileName =AppUtil.getBaseImagePath()+subPath+File.separator+imageName;
			log.info(imageName);
			File file = new File(fileName);
			if(!file.exists()){
				Response.status(Status.NOT_FOUND).build();
			}
			String mt = new MimetypesFileTypeMap().getContentType(file);
			return Response.ok(file, mt).build(); 
		}catch(Exception e){
			ExceptionUtil.recodeException(e, log);
			throw e;
		}
		
	}
	
	@GET
	@Path("small/{subPath}/{imageName}")
	public Response getsmallImage(@PathParam("subPath") String subPath,@PathParam("imageName") String imageName){
		try{
			final String fileName =AppUtil.getBaseImagePath()
					+subPath+File.separator
					+"small"+File.separator+imageName;
			log.info(imageName);
			File file = new File(fileName);
			if(!file.exists()){
				Response.status(Status.NOT_FOUND).build();
			}
			String mt = new MimetypesFileTypeMap().getContentType(file);
			return Response.ok(file, mt).build(); 
		}catch(Exception e){
			ExceptionUtil.recodeException(e, log);
			throw e;
		}
		
	}
	
}
