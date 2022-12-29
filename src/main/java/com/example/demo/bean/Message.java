package com.example.demo.bean;



import java.util.Date;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonFormat;

@Component
public class Message {

	private String nick_name;
	private String user_account;
	private String message;
	@JsonFormat(pattern="yyyy-MM-dd HH:mm",timezone = "GMT+8") //這段
	private Date message_time;//java.util的才能顯示到分鐘
	private Integer message_id;
	private Integer like_num;//join來的 資料庫沒有 但沒關係
	private String like_yn;
	private Integer sub_message_num;

	public Integer getSub_message_num() {
		return sub_message_num;
	}
	public void setSub_message_num(Integer sub_message_num) {
		this.sub_message_num = sub_message_num;
	}
	public Integer getLike_num() {
		return like_num;
	}
	public void setLike_num(Integer like_num) {
		this.like_num = like_num;
	}
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
	public String getLike_yn() {
		return like_yn;
	}
	public void setLike_yn(String like_yn) {
		this.like_yn = like_yn;
	}
	
	
	
}
