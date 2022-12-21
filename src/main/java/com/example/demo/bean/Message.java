package com.example.demo.bean;



import java.util.Date;

import org.springframework.stereotype.Component;

@Component
public class Message {

	private String nick_name;
	private String user_account;
	private String message;
	private Date message_time;//java.util的才能顯示到分鐘
	private Integer message_id;
	
	
	
	public Message() {
		super();
	}
	public String getNick_name() {
		return nick_name;
	}
	public void setNick_name(String nick_name) {//get set方法名稱很重要，行參會抓
		this.nick_name = nick_name;
	}
	public String getUser_account() {
		return user_account;
	}
	public void setUser_account(String user_account) {
		this.user_account = user_account;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Date getMessage_time() {
		return message_time;
		
	}
	
//	public String getMessage_time() {
//		SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd hh:mm:ss");
//		return ft.format(message_time);
//		
//	}//不行這樣 封裝會出問題
	public void setMessage_time(Date message_time) {
		this.message_time = message_time;
	}
	public Integer getMessage_id() {
		return message_id;
	}
	public void setMessage_id(Integer message_id) {
		this.message_id = message_id;
	}
	
	
	
}
