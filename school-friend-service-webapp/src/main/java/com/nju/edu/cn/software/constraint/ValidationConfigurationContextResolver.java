package com.nju.edu.cn.software.constraint;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.FormParam;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.ext.ContextResolver;

import org.glassfish.jersey.server.validation.ValidationConfig;
import org.hibernate.validator.internal.engine.DefaultParameterNameProvider;

public class ValidationConfigurationContextResolver implements ContextResolver<ValidationConfig> {
    @Override
    public ValidationConfig getContext( final Class<?> type ) {
        final ValidationConfig config = new ValidationConfig();
        config.parameterNameProvider( new RestAnnotationParameterNameProvider());
       
        return config;
    }
    

    static class RestAnnotationParameterNameProvider extends DefaultParameterNameProvider {
        @Override
        public List<String> getParameterNames( Method method ) {
            Annotation[][] annotationsByParam = method.getParameterAnnotations();
          
            List<String> names = new ArrayList<>( annotationsByParam.length );
            int index = 0;
            for ( Annotation[] annotations : annotationsByParam ) {
                String name = getParamName( annotations );
                if ( name == null )
                    name = method.getParameterTypes()[index].getSimpleName();;
                names.add( name );
                index++;
            }       
            return names;
        }

        private static String getParamName( Annotation[] annotations ) {
            for ( Annotation annotation : annotations ) {
                if ( annotation.annotationType() == QueryParam.class ) {
                    return QueryParam.class.cast( annotation ).value();
                }
                else if ( annotation.annotationType() == PathParam.class ) {
                    return PathParam.class.cast( annotation ).value();
                }
                else if ( annotation.annotationType() == FormParam.class ) {
                    return FormParam.class.cast( annotation ).value();
                } 
            }
            return null;
        }
    }
}