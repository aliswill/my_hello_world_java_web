package com.example.demo.jdbc;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootTest
public class MyJdbcTemplateTest {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Test
	public void testConnection() {
		
//		System.out.println(jdbcTemplate.getClass());
		String sql = "SELECT TOP 1 *  FROM [sunny_database].[dbo].[femaleBear]";
		
		List<Map<String, Object>> queryForList = jdbcTemplate.queryForList(sql);
		
		System.out.println(queryForList);
		
	}
	
}
