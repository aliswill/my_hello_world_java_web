package com.example.demo.jdbc;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;

import com.example.demo.repository.UserRepository;
@SpringBootTest
public class UserUpdateTest {
	@Resource
	private DataSource datasource;
	
	@Resource
	private JdbcTemplate jdbctemplate;
	
	@Autowired
	UserRepository u;
//	@Test
//	public void addUser(String useraccount,String userpassword,String email,String access_level) {
//		StringBuilder sql = new StringBuilder();
//		sql.append("INSERT INTO [user](useraccount,userpassword,email,access_level) VALUES(?,?,?,?)");
//		jdbctemplate.update(sql.toString(), new PreparedStatementSetter() {
//			@Override
//			public void setValues(PreparedStatement ps) throws SQLException {
//				ps.setString(1, useraccount);
//				ps.setString(2, userpassword);
//				ps.setString(3, email);
//				ps.setString(4, access_level);		
//			}
//		});
//	}
	@Test //要測是的目標方法不要寫在這個類 這邊直接調用 然後對方法點右鍵run as junit test
	public void test() {
		//UserRepository u = new UserRepository(); 不能用new的?要用@Autowired?
		u.addUser("account","1234","sss@gmail.com","1");
		System.out.println("成功");
	}
	
	@Test
	public void test2() {
		if(u.checkAcPd("account","123")) {
			System.out.println("success");
		}else {
			System.out.println("failed");
		}
		
		
	}
}
