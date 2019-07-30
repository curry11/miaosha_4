package com.miaosha.exception;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.miaosha.result.CodeMsg;
import com.miaosha.result.Result;

//全局异常处理
@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {
	
	@ExceptionHandler(value=Exception.class)
	public Result<String> exceprionHunder(HttpServletRequest request,Exception e){
		e.printStackTrace();
		if(e instanceof GlobalException) {
			GlobalException ex = (GlobalException)e;
			return Result.Error(ex.getCm());
		}else if(e instanceof BindException) {
			BindException ex = (BindException)e;
			
			List<ObjectError> errors = ex.getAllErrors();
			ObjectError error = errors.get(0);
			String msg = error.getDefaultMessage();
			return Result.Error(CodeMsg.BIND_ERROR.fillArgs(msg));
		}else {
			return Result.Error(CodeMsg.SERVER_ERROR);
		}
		
	}

}
