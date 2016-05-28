package com.nju.edu.cn.software.constraint;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CheckValidatorClass implements ConstraintValidator<CheckCase,String> {

	private CaseMode caseMode;
	@Override
	public void initialize(CheckCase checkCase) {
		// TODO Auto-generated method stub
		this.caseMode = checkCase.value();
	}

	@Override
	public boolean isValid(String object, ConstraintValidatorContext arg1) {
		// TODO Auto-generated method stub
		if(object == null){
			return true;
		}
		if(caseMode == CaseMode.UPPER){
			return object.equals(object.toUpperCase());
		} else{
			return object.equals(object.toLowerCase());
		}
		 
	}

}
