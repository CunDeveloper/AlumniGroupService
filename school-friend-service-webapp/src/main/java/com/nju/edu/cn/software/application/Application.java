package com.nju.edu.cn.software.application;

import javax.servlet.http.HttpSession;

import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.media.sse.SseFeature;
import org.glassfish.jersey.process.internal.RequestScoped;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.ServerProperties;

public class Application extends ResourceConfig {

	public Application(){
		  register(org.glassfish.jersey.server.filter.UriConnegFilter.class);
		  register(com.nju.edu.cn.software.filter.PoweredByResponseFilter.class);
		  register(com.nju.edu.cn.software.filter.AuthorizationRequestFilter.class);
		  register(com.nju.edu.cn.software.exception.CustomBadRequestException.class);
		  register(com.nju.edu.cn.software.exception.CustomClientErrorException.class);
		  register(com.nju.edu.cn.software.exception.CustomForbiddenException.class);
		  register(com.nju.edu.cn.software.exception.CustomInternalServerErrorException.class);
		  register(com.nju.edu.cn.software.exception.CustomNullPointerException.class);
		  register(com.nju.edu.cn.software.exception.CustomBadRequestExceptionNull.class);
		  register(com.nju.edu.cn.software.exception.CustomNotAcceptableException.class);
		 // 
		  register(com.nju.edu.cn.software.application.JacksonMapperProvider.class);
		  register(com.nju.edu.cn.software.exception.CustomJsonParseException.class);
		  register(com.nju.edu.cn.software.exception.CustomNotAllowedException.class);
		  register(com.nju.edu.cn.software.exception.CustomNotAuthorizedException.class);
		  register(com.nju.edu.cn.software.exception.CustomNotFoundException.class);
		  register(com.nju.edu.cn.software.exception.CustomNotSupportedException.class);
		  register(com.nju.edu.cn.software.exception.CustomServerErrorException.class);
		  register(com.nju.edu.cn.software.exception.CustomRedirectionException.class);
		  register(com.nju.edu.cn.software.exception.CustomServiceUnavailableException.class);
		  register(com.nju.edu.cn.software.exception.CustomWebApplicationException.class);
		  register(org.glassfish.jersey.jackson.JacksonFeature.class);
		  register(com.nju.edu.cn.software.constraint.ValidationConfigurationContextResolver.class);
		  register(com.nju.edu.cn.software.exception.CustomConstraintViolationException.class);
		  register(org.glassfish.jersey.media.multipart.MultiPartFeature.class);
		  register(SseFeature.class);
		  
		  register(new AbstractBinder() {
	            @Override
	            protected void configure() {
	                bindFactory(HttpSessionFactory.class).to(HttpSession.class)
	                .proxy(true).proxyForSameScope(false).in(RequestScoped.class);
	            }
	        });
	      property(ServerProperties.METAINF_SERVICES_LOOKUP_DISABLE, true);
	      property(ServerProperties.BV_SEND_ERROR_IN_RESPONSE, true);
	}
}
