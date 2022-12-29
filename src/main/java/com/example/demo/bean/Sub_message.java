package com.example.demo.bean;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Sub_message {
	
	private Integer message_id;
	private Integer sub_message_id;
	private String nick_name;
	private String user_account;	
	@JsonFormat(pattern="yyyy-MM-dd HH:mm",timezone = "GMT+8") 
	private Date message_time;//java.util的才能顯示到分鐘
	private String message;
//	private Integer like_num;//
//	private String like_yn;
	
	
	
	public Integer getMessage_id() {
		return message_id;
	}
	public Sub_message() {
		super();
	}
	public Sub_message(Integer message_id, Integer sub_message_id, String nick_name, String user_account, Date message_time,
			String message) {
		super();
		this.message_id = message_id;
		this.sub_message_id = sub_message_id;
		this.nick_name = nick_name;
		this.user_account = user_account;
		this.message_time = message_time;
		this.message = message;
	}
	public void setMessage_id(Integer message_id) {
		this.message_id = message_id;
	}
	public Integer getSub_message_id() {
		return sub_message_id;
	}
	public void setSub_message_id(Integer sub_message_id) {
		this.sub_message_id = sub_message_id;
	}
	public String getNick_name() {
		return nick_name;
	}
	public void setNick_name(String nick_name) {
		this.nick_name = nick_name;
	}
	public String getUser_account() {
		return user_account;
	}
	public void setUser_account(String user_account) {
		this.user_account = user_account;
	}
	public Date getMessage_time() {
		return message_time;
	}
	public void setMessage_time(Date message_time) {
		this.message_time = message_time;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	
}
