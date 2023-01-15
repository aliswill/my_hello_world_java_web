package com.example.demo.repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.annotation.Resource;
import javax.annotation.processing.SupportedSourceVersion;
import javax.sql.DataSource;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.example.demo.bean.Employee;
import com.example.demo.bean.User;
import com.example.demo.utils.MyJdbcTemplate;

@Component
@Repository
public class UserRepository {
	@Resource
	private DataSource datasource;
	
	@Resource
	private JdbcTemplate jdbctemplate;
	
	@Resource
	private MyJdbcTemplate myjdbctemplate;
	
	public void addUser(String useraccount,String userpassword,String email,String access_level) {
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO [user](useraccount,userpassword,email,access_level) VALUES(?,?,?,?)");
		jdbctemplate.update(sql.toString(), new PreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setString(1, useraccount);
				ps.setString(2, userpassword);
				ps.setString(3, email);
				ps.setString(4, access_level);		
			}
		});
	}
	
	public boolean accountExistYn(String useraccount) {//註冊時檢查帳號是否已經存在
		StringBuilder sql = new StringBuilder();
		sql.append("select useraccount from [sunny_database].[dbo].[user] where useraccount = ?");		
		String res = myjdbctemplate.queryForObject(sql.toString(), String.class,useraccount);
		if(res == null) {
			return false;
		}else {
			return true;
		}
	}
	
	public boolean checkAcPd(String useraccount, String userpassword) {//登入時檢查帳號密碼
		StringBuilder sql = new StringBuilder();
		sql.append("select * from  [sunny_database].[dbo].[user] where useraccount = ? and userpassword = ?");
		RowMapper<User> rowmapper = new BeanPropertyRowMapper<>(User.class);	
		User user = myjdbctemplate.queryForObject(sql.toString(), rowmapper, useraccount,userpassword);
		if(user!=null) { //嘗試用自己的myjdbctemplate解決查尋不到會拋例外的情況
			System.out.println("帳號密碼存在");
			return true;
		}else {
			System.out.println("帳號或密碼錯誤");
			return false;
		}
//		try {                           
//			//查不到會拋例外 而不是回傳null
//			User user = jdbctemplate.queryForObject(sql.toString(), rowmapper, useraccount,userpassword);
//		}catch(DataAccessException e) {
//			System.out.println("帳號或密碼錯誤");
//			return false;
//		}		
//		System.out.println("帳號密碼存在");
//		return true;

	}
	
	public String getHeadUrl(String useraccount) {
		String HeadUrl;
		StringBuilder sql = new StringBuilder();
		sql.append("select head from  [sunny_database].[dbo].[user] where useraccount = ? ");
		RowMapper<String> rowmapper = new BeanPropertyRowMapper<>(String.class);	
		//錯誤原因不明 String HeadUrl = myjdbctemplate.queryForObject(sql.toString(), rowmapper, useraccount);
		//String HeadUrl = jdbctemplate.queryForObject(sql.toString(), String.class, new Object[] {useraccount});
		//jdbctemplate.queryForObject(sql.toString(),new Object[] {useraccount},rowmapper);
		try {
		HeadUrl = jdbctemplate.queryForObject(sql.toString(),String.class,useraccount);
		}catch(Exception e) {
			return null;
		}
		return HeadUrl;
	}
}
