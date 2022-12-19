package com.example.demo.jdbc;

import javax.servlet.http.HttpSession;

import org.apache.catalina.session.StandardSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import com.example.demo.repository.MessageRepository;
import com.example.demo.repository.UserRepository;

@SpringBootTest
public class MessageTest {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	MessageRepository u;
	
//	public void test() {//不知道如何仿製session來作單元測試
//		HttpSession session = new StandardSession();
//		session.setAttribute("loginUser", "banana");
//		//UserRepository u = new UserRepository(); 不能用new的?要用@Autowired?
//		u.addMessge(null, null, null);
//	}
}
