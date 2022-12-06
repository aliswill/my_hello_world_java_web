package com.example.demo.utils;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.dao.DataAccessException;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;


//建議不要用繼承的 因為在配置上會很麻煩 要去參考被繼承的類原本是怎麼配置 而不是簡單回傳就好
@Repository
public class MyJdbcTemplate{

	@Resource
	private DataSource datasource;
	
	@Resource
	private JdbcTemplate jdbctemplate;
	
	
	public <T> T queryForObject(String sql, RowMapper<T> rowMapper, Object... args){
		T res;
		try {
			res =jdbctemplate.queryForObject(sql, rowMapper, args);
		}catch(DataAccessException e) {			
			return null;
		}
		return res;
	}
	
	public <T> T queryForObject(String sql, Class<T> requiredType, @Nullable Object... args)  {
		
		T res;
		try {
			res = jdbctemplate.queryForObject(sql, requiredType,args);
		}catch(DataAccessException e) {			
			return null;
		}
		return res;
		
		//return queryForObject(sql, args, getSingleColumnRowMapper(requiredType));
	}
	

}
