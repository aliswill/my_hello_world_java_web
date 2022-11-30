package com.example.demo.dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.example.demo.bean.Employee;

@Repository
public class EmployeeDao {
	//static SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
	private static Map<String,Employee> employees = null;
	
	//把需要try-catch的方法獨立出來寫(再這邊寫靜態方法湊合就好)
	//如果你調的方法 他有throw exception 那你自己就必須try-catch
	//但你調的的方法 他有try-catch 那不代表你自己也必須try-catch
	static Date returnDate(String date) {
		try {
			return new SimpleDateFormat("yyyyMMdd").parse(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	static {
		employees = new HashMap<String,Employee>();
		employees.put("A001", new Employee("A001","笨棋","軟體工程師",895000,returnDate("20190102")));
		employees.put("A002", new Employee("A002","肥棋","睡覺工程師",795000,returnDate("20170102")));
		employees.put("A003", new Employee("A003","胖棋","巧克力工程師",595000,returnDate("20180107")));
		employees.put("A004", new Employee("A004","蠢棋","神遊工程師",955000,returnDate("20200104")));
		employees.put("A005", new Employee("A005","跩棋","讀書工程師",1025000,returnDate("20150502")));
		
	}
	
	public void save(Employee employee) {
		employees.put(employee.getId(),employee);
	}

	public Collection<Employee> getAll(){
		return employees.values();
	}
	
	public Employee getEmployee(String id) {
		return employees.get(id);
	}
	
	public void delEmployee(String id) {
		employees.remove(id);
	}
}
