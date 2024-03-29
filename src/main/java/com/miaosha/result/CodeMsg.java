package com.miaosha.result;

public class CodeMsg {

	private int code;
	private String msg;
	
	public static CodeMsg SUCCESS = new CodeMsg(0,"success");
	public static CodeMsg SERVER_ERROR = new CodeMsg(500100,"服务端异常");
	public static CodeMsg PASSWORD_EMPTY = new CodeMsg(500101,"密码不能为空");
	public static CodeMsg MOBILE_EMPTY = new CodeMsg(500102,"手机号不能为空");
	public static CodeMsg MOBILE_ERROR = new CodeMsg(500103,"手机号格式錯誤");
	public static CodeMsg MOBILE_NULL = new CodeMsg(500104,"手机号不存在");
	public static CodeMsg PASSWORD_ERROE = new CodeMsg(500105,"密码输入错误");
	public static CodeMsg BIND_ERROR = new CodeMsg(500111,"参数校验异常: %s");

	//秒杀模块
	public static CodeMsg GOODS_NULL = new CodeMsg(500112,"商品秒杀完毕");
	public static CodeMsg GOODS_NULLs = new CodeMsg(500152,"sssss");

	
	private CodeMsg(int code, String msg) {
		this.code = code;
		this.msg = msg;
		
	}
	
	
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	public CodeMsg fillArgs(Object... args) {
		int code = this.code;
		String message = String.format(this.msg, args);
		return new CodeMsg(code,message);
	}
	
	
}
