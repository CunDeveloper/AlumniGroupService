package com.nju.edu.cn.software.constraint;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.*;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target({METHOD,FIELD,ANNOTATION_TYPE})
@Retention(RUNTIME)
@Constraint(validatedBy=CheckValidatorClass.class)
@Documented
public @interface CheckCase {

	String message() default"{com.nju.edu.cn.software.constraint.checkcase}";
	
	Class<?>[]groups()default{};
	
	Class<?extends Payload>[]payload()default{};
	
	CaseMode value();
}
