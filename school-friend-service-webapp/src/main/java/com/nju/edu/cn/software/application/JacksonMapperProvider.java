package com.nju.edu.cn.software.application;

import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonParser.Feature;
import com.fasterxml.jackson.databind.ObjectMapper;
@Provider
public class JacksonMapperProvider implements ContextResolver<ObjectMapper> {
    private final ObjectMapper defaultMapper;
    public JacksonMapperProvider() {
        defaultMapper = createDefaultMapper();
       
    }

    private static ObjectMapper createDefaultMapper() {
        return new ObjectMapper()
            .setSerializationInclusion(Include.ALWAYS)
            .configure(Feature.ALLOW_COMMENTS, true)
            .configure(Feature.ALLOW_UNQUOTED_FIELD_NAMES, true)
            .configure(Feature.ALLOW_SINGLE_QUOTES, true)
            .configure(Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true)
        .configure(Feature.ALLOW_BACKSLASH_ESCAPING_ANY_CHARACTER, true);
    }

   

    @Override
    public ObjectMapper getContext(Class<?> type) {
		return defaultMapper;
         
    }
}
