package com.nju.edu.cn.software.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;
import org.glassfish.jersey.media.multipart.FormDataBodyPart;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataMultiPart;
import org.springframework.beans.factory.annotation.Autowired;

 
 

/**
 * Root resource (exposed at "myresource" path)
 */
@Path("myresource")
public class MyResource {

//	private static final Logger log = Logger.getLogger(MyResource.class);
//	@Autowired
//	private UserService dao;
//	@Autowired
//	private TestMapper mapper;
//	@Autowired
//	private BlogMapper blogMapper;
//	@Autowired
//	private AuthorMapper authorMapper;
//	@Autowired
//	private UserBatchService userService;
//    /**
//     * Method handling HTTP GET requests. The returned object will be sent
//     * to the client as "text/plain" media type.
//     *
//     * @return String that will be returned as a text/plain response.
//     */
//    @GET
//    @Produces(MediaType.APPLICATION_JSON)
//    public Response getIt() {
//    	Response response = null;
//    	ExceptionInfo exceptInfo = new ExceptionInfo();
//    	RespUser respUser = new RespUser();
//    	List<ExceptionInfo> exceptions = new ArrayList<>();
//    	 try{
//    		User user = null ;
//    		user.setId(1);
//    		dao.getUser("dddxxx");
//    		dao.saveUser(null);
//    		user.setName("zhangsan");
//    		exceptInfo.setCode(200);
//    		exceptInfo.setMsg("ok");
//    		exceptions.add(exceptInfo);
//    		respUser.setUser(user);
//    		respUser.setExceptions(exceptions);
//         	response =Response.ok().entity(respUser).build();
//    	 }catch(Exception e){
//    		if(e instanceof NullPointerException){
//    			NullPointerException ex = new NullPointerException();
//    			 exceptInfo.setCode(433);
//    	    	  exceptInfo.setMsg("not point exception");
//    	          exceptions.add(exceptInfo);
//    	    		respUser.setExceptions(exceptions);
//    	    		response = Response.ok(respUser).build();
//    		}
//    	   
//    	 }
//    	 return response;
//    }
//    
//    @POST
//    @Path("saveUser")
//    @Produces(MediaType.TEXT_PLAIN)
//    @Consumes(MediaType.APPLICATION_JSON)
//    public String saveUser(@Valid User user){
//    	log.info(user.getName());
//    	int result = dao.saveUser(user);
//    	return "ok" +result;
//    }
//    
//    @GET
//    @Path("getUser/{userId}")
//    @Produces(MediaType.TEXT_PLAIN)
//    public String getUserName(@PathParam("userId") String userId){
//    	userService.insertUsers();
//    	return dao.getUser(userId).getName();
//    }
//    
//    @POST
//    @Path("multipleFiles")
//    @Produces(MediaType.TEXT_PLAIN)
//    @Consumes(MediaType.MULTIPART_FORM_DATA)    
//    public String post(FormDataMultiPart multiPart) {
//         StringBuilder builder = new StringBuilder();
//         builder.append("filed size = "+multiPart.getFields().size()+"\n");
//         Map<String,List<FormDataBodyPart>> maps = multiPart.getFields();
//         for(Entry<String,List<FormDataBodyPart>> entry:maps.entrySet()){
//        	 if(entry.getKey().equals("file")){
//        		 FormDataContentDisposition contentDisposition=entry.getValue().get(0).getFormDataContentDisposition();
//        		 InputStream in = entry.getValue().get(0).getEntityAs(InputStream.class);
//        		 saveToDisk(in,contentDisposition);
//        		 builder.append("\nfileName="+contentDisposition.getFileName()+"=name="+contentDisposition.getName());
//        	 }else{
//        		 builder.append("\n"+entry.getKey()+"===value==="+entry.getValue().get(0).getValue());
//        	 }
//        	 
//         }
//         return builder.toString();
//    }
//    
//    private void saveToDisk(InputStream in,
//            FormDataContentDisposition filedetail) {
//        // TODO Auto-generated method stub
//
//        String location = "C:/Users/xiaojuzhang/upload";
//        File file = new File(location);
//        if(!file.exists()){
//        	file.mkdir();
//        }
//        try{
//            OutputStream out = new FileOutputStream(new File(file,filedetail.getFileName()));
//            int read = 0;
//            byte[] bytes = new byte[1024];
//            while((read = in.read(bytes)) != -1){
//                out.write(bytes,0,read);
//            }
//            out.flush();
//
//            out.close();
//        }catch (IOException e){
//            e.printStackTrace();
//            log.error(e.getMessage()+e.getCause().getMessage());
//        }
//    }
//    
//    
//   
}
