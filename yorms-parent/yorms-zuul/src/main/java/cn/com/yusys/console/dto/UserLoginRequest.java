package cn.com.yusys.console.dto;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;

public class UserLoginRequest implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "用户名",required = true)
    private String userName;
	
	/**
	 * 密码，密文存储
	 */
	@ApiModelProperty(value = "密码",required = true)
    private String password;
	
	
	/**
	 * 验证码
	 */
	@ApiModelProperty(value = "验证码",required = false)
    private String captcha;


	public String getUserName() {
		return userName;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getCaptcha() {
		return captcha;
	}


	public void setCaptcha(String captcha) {
		this.captcha = captcha;
	}
	
	
}
