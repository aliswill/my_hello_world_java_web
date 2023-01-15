package com.example.demo.jdbc;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.example.demo.utils.MyJdbcTemplate;

@SpringBootTest
public class UserTest {

	@Autowired
	private MyJdbcTemplate myjdbctemplate;
	
	@Test
	public void getHeadUrl(String useraccount) {
		StringBuilder sql = new StringBuilder();
		sql.append("select head from  [sunny_database].[dbo].[user] where useraccount = ? ");
		RowMapper<String> rowmapper = new BeanPropertyRowMapper<>(String.class);	
		String HeadUrl = myjdbctemplate.queryForObject(sql.toString(), rowmapper, useraccount);
		System.out.println(HeadUrl);
		
	}
	
	
	
}
