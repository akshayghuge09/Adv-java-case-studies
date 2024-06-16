package com.user_login;

public class UserInfo {
	
	private String userName;
	private String password;
	
	public UserInfo() {
		// TODO Auto-generated constructor stub
	}

	public UserInfo(String userName, String password) {
		super();
		this.userName = userName;
		this.password = password;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getpassword() {
		return password;
	}

	public void setpassword(String password) {
		this.password = password;
	}
	

}
