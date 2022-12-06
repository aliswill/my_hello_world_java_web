package com.example.demo.bean;

import org.springframework.stereotype.Component;

@Component
public class User {
	private String useraccount;
	private String userpassword;
	private String email;
	private String access_level;
	
		
	public User() {
		super();
	}
	
	public User(String useraccount, String userpassword, String email, String access_level) {
		super();
		this.useraccount = useraccount;
		this.userpassword = userpassword;
		this.email = email;
		this.access_level = access_level;
	}
	public String getUseraccount() {
		return useraccount;
	}
	public void setUseraccount(String useraccount) {
		this.useraccount = useraccount;
	}
	public String getUserpassword() {
		return userpassword;
	}
	public void setUserpassword(String userpassword) {
		this.userpassword = userpassword;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAccess_level() {
		return access_level;
	}
	public void setAccess_level(String access_level) {
		this.access_level = access_level;
	}
	
	
}
