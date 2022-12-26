package com.example.demo.repository;

import java.util.List;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.example.demo.bean.Message;

@Component
@Repository //這個類好像用不太到...
public class MessageLikeRepository {

	@Resource
	private DataSource datasource;
	
	@Resource
	private JdbcTemplate jdbctemplate;
	
	
	public int getLikeNum(String message_id) {
		StringBuilder sql = new StringBuilder();
		sql.append("  SELECT count(user_account)"
				+ "  FROM message_like"
				+ "  WHERE message_id = ?");
		int like_num = 0;	
		like_num = jdbctemplate.queryForObject(sql.toString(), Integer.class, message_id);

		return like_num;
	}
	
	public boolean alreadyLikeYn(String user_account,String message_id) {//看此帳戶是否有按此則貼文讚
		StringBuilder sql = new StringBuilder();
		sql.append("  SELECT count(user_account)"
				+ "  FROM message_like"
				+ "  WHERE user_account = ? and message_id = ?");
		
		int like_yn = 0;	
		like_yn = jdbctemplate.queryForObject(sql.toString(),Integer.class,user_account,message_id);
		if(like_yn>0) {
			System.out.println("已經按讚" + user_account + message_id);
			return true;
		}else {
			System.out.println("還沒按過讚"+ user_account + message_id);
			return false;
		}
//		try {
//			like_yn = jdbctemplate.queryForObject(sql.toString(),String.class,user_account,message_id);
//			
//			System.out.println("已經按讚" + user_account + message_id);
//			return true;
//		}catch(DataAccessException e) {//查不到的話
//			System.out.println("還沒按過讚"+ user_account + message_id);
//			return false;
//		}
	}
	
	public void changeLike(String user_account,String message_id) {
		if(alreadyLikeYn(user_account,message_id)) {//若已經按過讚，則再按一次取消
			StringBuilder sql = new StringBuilder();
			sql.append("  delete"
					+ "  FROM message_like"
					+ "  WHERE user_account = ? and message_id = ?");
			
			jdbctemplate.update(sql.toString(),user_account,message_id);
			System.out.println("刪除了"+user_account+message_id);
			
		}else {//按讚
			StringBuilder sql = new StringBuilder();
			sql.append("  insert into message_like (user_account,message_id)"
					+ "  values (?,?) ");
			jdbctemplate.update(sql.toString(),user_account,message_id);
			System.out.println("新增了"+user_account+message_id);
			
		}
	}
	
	
	}

