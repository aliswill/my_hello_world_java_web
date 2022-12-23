package com.example.demo.bean;

public class Message_like {

	private String user_account;
	private String message_id;

	
	
	
	public String getMessage_id() {
		return message_id;
	}
	public void setMessage_id(String message_id) {
		this.message_id = message_id;
	}
	public Message_like(String user_account,String message_id) {
		super();
		this.user_account = user_account;
		this.message_id = message_id;
	}
	public Message_like() {
		super();
	}
	public String getUser_account() {
		return user_account;
	}
	public void setUser_account(String user_account) {
		this.user_account = user_account;
	}

	
}
