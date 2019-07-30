package com.miaosha.exception;

import com.miaosha.result.CodeMsg;

//全局异常处理之CodeMsg
public class GlobalException extends RuntimeException {
	
	private static final long serivalVersionUID = 1L;
	
	private CodeMsg cm;
	
	public GlobalException(CodeMsg cm) {
		super(cm.toString());
		this.cm = cm;
	}

	public CodeMsg getCm() {
		return cm;
	}



}
