package com.example.demo.bean;

import java.util.Date;

import org.springframework.stereotype.Component;

@Component
public class Employee {
	
	private String id;
	private String name;
	private String position;
	private int pay;
	private Date on_board_date;
	
	public Employee(String id, String name, String position, int pay, Date on_board_date) {
		super();
		this.id = id;
		this.name = name;
		this.position = position;
		this.pay = pay;
		this.on_board_date = on_board_date;
	}
	
	public Employee() {//不能沒有空參建夠子
		
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public int getPay() {
		return pay;
	}

	public void setPay(int pay) {
		this.pay = pay;
	}

	public Date getOn_board_date() {
		return on_board_date;
	}

	public void setOn_board_date(Date on_board_date) {
		this.on_board_date = on_board_date;
	}
	
	

	
	
	
}
