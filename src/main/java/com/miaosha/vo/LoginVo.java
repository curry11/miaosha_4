package com.miaosha.vo;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.miaosha.validator.IsMobile;

public class LoginVo {           //@NotNull 为参数校验
	
	@NotNull
	@IsMobile
	private String mobile;
	@NotNull
	@Length(min=32)
	private String password;

	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public String toString() {
		return "LoginVo [mobile=" + mobile + ", password=" + password + "]";
	}

}
