package com.example.demo.repository;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.example.demo.bean.Employee;
import com.example.demo.bean.Message;

@Component
@Repository
public class MessageRepository {

	@Resource
	private DataSource datasource;
	
	@Resource
	private JdbcTemplate jdbctemplate;
	
	
	
	public List<Message> getMessageList(){
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT nick_name,user_account,message,message_time,message_id FROM message order by message_id");
		RowMapper<Message> rowmapper = new BeanPropertyRowMapper<>(Message.class);
		List<Message> allMessage = jdbctemplate.query(sql.toString(), rowmapper);		
		return allMessage;
	}
	
	public boolean checkAccount(Integer message_id,String user_account) {
		StringBuilder sql = new StringBuilder();
		sql.append(" select user_account from message where message_id = ?");
		String message_user_account;		
		try {
			message_user_account = jdbctemplate.queryForObject(sql.toString(), String.class);
			return true;
		}catch(DataAccessException e) {//查不到的話
			return false;
		}
	}
	
	public void deleteMessageById(Integer message_id){
		StringBuilder sql = new StringBuilder();
		sql.append("delete FROM message where message_id =?");
		RowMapper<Message> rowmapper = new BeanPropertyRowMapper<>(Message.class);
		jdbctemplate.update(sql.toString(), message_id);
		System.out.println("成功刪除留言"+message_id);				
	}
	
	//暱稱跟內容來自FORM，時間由程式抓取，ID由程式產生，帳號由SESSION抓
	public void addMessge(String nickname,String message,HttpSession session) {
		StringBuilder sql = new StringBuilder();
		sql.append(" select top (1) message_id from message order by message_id desc");
		//RowMapper<Integer> rowmapper = new BeanPropertyRowMapper<>(Integer.class);
		Integer last_id;		
		try {
			last_id = jdbctemplate.queryForObject(sql.toString(), Integer.class);
		}catch(DataAccessException e) {//查不到的話
			last_id = 0;
		}
		
		Integer message_id = last_id+1;
		
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String message_time = dtf.format(LocalDateTime.now());//message_time，字串可傳嗎?
        
        String user_account = (String) session.getAttribute("loginUser");
        
        StringBuilder sql1 = new StringBuilder();
		sql1.append("INSERT INTO [message](nick_name,user_account,message,message_time,message_id) VALUES(?,?,?,?,?) ");
		jdbctemplate.update(sql1.toString(), new PreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setString(1,nickname);
				ps.setString(2,user_account);
				ps.setString(3,message);
				ps.setString(4,message_time);
				ps.setInt(5,message_id);
				
			}
		});
		System.out.println("資料插入成功!");
	}
}
