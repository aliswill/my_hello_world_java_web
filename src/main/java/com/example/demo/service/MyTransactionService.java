package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MyTransactionService {

	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@Transactional
	public void testInsert() {
		
		String sql = "INSERT INTO [cal].[SYS_PROPERTY] values (?,?,?)";
		jdbcTemplate.update(sql, "DEBUG3", "Y", "產出驗證用DETAIL資料3");
		
	}

	
	@Transactional(noRollbackFor = RuntimeException.class)
	public void testInsert2() {
		
		String sql = "INSERT INTO [cal].[SYS_PROPERTY] values (?,?,?)";
		jdbcTemplate.update(sql, "DEBUG3", "Y", "產出驗證用DETAIL資料3");
		
		throw new RuntimeException("rollbacktest");
	}
	
}
