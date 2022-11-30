package com.example.demo.repository;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.example.demo.bean.Employee;

@Component
@Repository
public class EmployeeRepository {
	@Resource
	private DataSource datasource;
	
	@Resource
	private JdbcTemplate jdbctemplate;
	
	
	public String testEmployeeRepository() {
		return "testEmployeeRepository方法";
	}
	
	public List<Employee> getEmployeeList(){
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT id,name,position,pay,on_board_date FROM employee order by id");
		RowMapper<Employee> rowmapper = new BeanPropertyRowMapper<>(Employee.class);
		List<Employee> allEmployee = jdbctemplate.query(sql.toString(), rowmapper);
		
		return allEmployee;
	}
	
	public Employee getEmployeeById(String emp_id){
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT id,name,position,pay,on_board_date FROM employee where id =?");
		RowMapper<Employee> rowmapper = new BeanPropertyRowMapper<>(Employee.class);
		Employee employee = jdbctemplate.queryForObject(sql.toString(), rowmapper, emp_id);
				//(sql.toString(), rowmapper);
		System.out.println(employee.getOn_board_date());
		return employee;
	}
	
	public void deleteEmployeeById(String emp_id){
		StringBuilder sql = new StringBuilder();
		sql.append("delete FROM employee where id =?");
		RowMapper<Employee> rowmapper = new BeanPropertyRowMapper<>(Employee.class);
		jdbctemplate.update(sql.toString(), emp_id);
		System.out.println("成功刪除員工"+emp_id);				
	}
	
	public void addEmployee(String name,String position,Integer pay,Date on_board_date) {
		StringBuilder sql0 = new StringBuilder();
		sql0.append(" select top (1) id from employee order by id desc");
		//RowMapper<Integer> rowmapper = new BeanPropertyRowMapper<>(Integer.class);
		Integer last_id;		
		try {
			last_id = jdbctemplate.queryForObject(sql0.toString(), Integer.class);
		}catch(DataAccessException e) {//查不到的話
			last_id = 0;
		}
		
		Integer id = last_id+1;
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO [employee](id,name,position,pay,on_board_date) VALUES(?,?,?,?,?) ");
		jdbctemplate.update(sql.toString(), new PreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setInt(1, id);
				ps.setString(2, name);
				ps.setString(3, position);
				ps.setInt(4, pay);
				ps.setDate(5, on_board_date);
				
			}
		});
		System.out.println("資料插入成功!");
	}
	
	public void alterEmployee(String id,String name,String position,Integer pay,Date on_board_date) {
		
		StringBuilder sql = new StringBuilder();
		sql.append("update employee set name=?,position=?,pay=?,on_board_date=? where id=? ");
		jdbctemplate.update(sql.toString(), new PreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setString(1,name );
				ps.setString(2,position );
				ps.setInt(3,pay );
				ps.setDate(4,on_board_date );
				ps.setString(5,id );
				
			}
		});
		System.out.println("資料修改成功!"+id);
	}
	
}
