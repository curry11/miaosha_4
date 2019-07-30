package com.miaosha.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.lang3.StringUtils;

import com.miaosha.util.ValidatorUtil;

public class IsMobileValidator implements ConstraintValidator<IsMobile,String> {

	//初始化可以拿到注解
	private boolean required = false;
	@Override
	public void initialize(IsMobile constraintAnnotation) {
		// TODO Auto-generated method stub
		required = constraintAnnotation.require();
		
	}

	//在这里判断是否合法
	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		// TODO Auto-generated method stub
		if(required) {
			return ValidatorUtil.isMobile(value);
		}else {
			if(StringUtils.isEmpty(value)) {
				return true;
				
			}else {
				return ValidatorUtil.isMobile(value);
			}
		}
	}

}
