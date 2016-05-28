package com.nju.edu.cn.software.service;

import java.io.IOException;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;
import org.glassfish.jersey.media.sse.EventOutput;
import org.glassfish.jersey.media.sse.OutboundEvent;
import org.glassfish.jersey.media.sse.SseFeature;
import org.springframework.beans.factory.annotation.Autowired;
 















import com.nju.edu.cn.software.helper.Constant;
import com.nju.edu.cn.software.mapper.AlumniTalkMapper;
import com.nju.edu.cn.software.mapper.AlumnicTalkPraiseMapper;

/**
 *  client sends a request and server holds a connection until a new message is ready, 
 *  then it sends the message back to the client while still keeping the connection open 
 *  so that it can be used for another message once it becomes available. Once a new message is ready, 
 *  it is sent back to the client on the same initial connection. Client processes the messages sent back 
 *  from the server individually without closing the connection after processing each message. So, 
 *  SSE typically reuses one connection for more messages (called events). 
 *  SSE also defines a dedicated media type that describes a simple format of individual events sent 
 *  from the server to the client
 * @author xiaojuzhang
 *
 */

@Path("events")
public class SseResource {
	private static final Logger log = Logger.getLogger(SseResource.class);
	@Autowired
	private AlumnicTalkPraiseMapper talkPraiseDao;
	
	@Autowired
	private AlumniTalkMapper talkDao;
	
	@POST
    @Produces(SseFeature.SERVER_SENT_EVENTS)
	@Consumes(MediaType.APPLICATION_JSON)
    public EventOutput getServerSentEvents(@Context ContainerRequestContext crc
    		,@Suspended final AsyncResponse asyncResponse) {
	 
        final EventOutput eventOutput = new EventOutput();
         final int authorId = (int) crc.getProperty(Constant.AUTHOR_ID);
         log.info("authorId"+authorId);
               new Thread(){
            	   @Override
            	   public void run(){
            		   try {
//                       	List<Integer> ids = talkDao.getTalkIdByAuthorId(authorId);
//                       	log.info(ids.size());
                       	int i =0;
                       	while(i<10){
                       		log.info("result:"+i);
                       		i++;
                       		Thread.sleep(1000);
                       	}
//                       	List<AlumnicTalkPraise> praises = talkPraiseDao.getTalkPraisesAboutAuthorByIds(ids);
//                           SchoolFriendGson gson = SchoolFriendGson.newInstance();
//                           String result = gson.toJson(praises);
//                           log.info("result:"+result);
//                       	log.info("paraise:"+praises.size());
//                       	
//                       	while(praises.size()!=4){
//                       		 
//                       		praises = talkPraiseDao.getTalkPraisesAboutAuthorByIds(ids);
//                       		log.info("paraise seconde:"+praises.size());
//                       	}
                       	 final OutboundEvent.Builder eventBuilder
                            = new OutboundEvent.Builder();
                            eventBuilder.name("message-to-client");
                            eventBuilder.data(String.class,
                                 "hello world");
                            final OutboundEvent event = eventBuilder.build();
                            eventOutput.write(event);
                            asyncResponse.resume(eventOutput);
                       	 
                       } catch (IOException | InterruptedException e) {
                           throw new RuntimeException(
                               "Error when writing the event.", e);
                       }   finally {
                           try {
                               eventOutput.close();
                               log.info("event output is closed");
                           } catch (IOException ioClose) {
                               throw new RuntimeException(
                                   "Error when closing the event output.", ioClose);
                           }
                       }
            
           
            	   }
               }.start();
               return eventOutput;    
	}
}
