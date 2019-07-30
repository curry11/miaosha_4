package com.miaosha.util;

import org.apache.commons.codec.digest.DigestUtils;

public class Md5Util {

	public static String md5(String src) {
		return DigestUtils.md5Hex(src);
	}
	
	private static final String salt ="6981234";
	
	public static String inputPassFormPass(String inputPass) {
		String str = ""+ salt.charAt(0)+salt.charAt(2)+inputPass+salt.charAt(5);
		return md5(str);
	}
	
	public static String formPassToDBPass(String formPass,String salt) {
		String str = ""+ salt.charAt(0)+salt.charAt(2)+formPass+salt.charAt(5);
		return md5(str);
	}
	
	public static String inputPassToPss(String input,String saltDB) {   //往数据库中插入密码进行两次md5加密
		String formPass = inputPassFormPass(input);
		String dbPass = formPassToDBPass(formPass, saltDB);
		return dbPass;
	}
	
	
	
	/*
	 * public static void main(String[] args) {
	 * System.out.println(inputPassFormPass("11111111111"));
	 * System.out.println(inputPassToPss("11111111111", salt)); }
	 */
	 
}
