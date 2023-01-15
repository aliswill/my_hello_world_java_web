package com.example.demo.bean;

import org.springframework.stereotype.Component;

@Component
public class User {
	private String useraccount;
	private String userpassword;
	private String email;
	private String access_level;
	private String head;
		
	public User() {
		super();
	}
	
	public String getHead() {
		return head;
	}

	public void setHead(String head) {
		this.head = head;
	}

	public User(String useraccount, String userpassword, String email, String access_level, String head) {
		super();
		this.useraccount = useraccount;
		this.userpassword = userpassword;
		this.email = email;
		this.access_level = access_level;
		this.head = head;
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
