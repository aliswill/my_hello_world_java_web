package com.example.demo.controller;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.bean.Employee;
import com.example.demo.repository.EmployeeRepository;

@Controller
public class EmployeeController {

	@Autowired
	EmployeeRepository employeerepository;
	
	@GetMapping("emp/emp_list")//來到員工資料表頁面
	public String getList() {
		return "emp/emp_list";
	}
	
	@RequestMapping(value="/getAllEmployees",method=RequestMethod.POST)
	public String getAllEmployee(Model model) {
		//嘗試在資料庫查詢然後儲存到model，再顯示於頁面
		//之後也要試試看使用restController查詢並顯示
		
		List<Employee> employees = employeerepository.getEmployeeList();
		model.addAttribute("testEmployeeRepository", employeerepository.testEmployeeRepository());
		model.addAttribute("employees",employees);
		model.addAttribute("test","model存取成功");
		
		return "emp/all_emp";
	}
	

	
	@RequestMapping(value="/addEmployee",method=RequestMethod.POST)
	public String addEmployee(String name,String position,Integer pay,Date on_board_date) {
		
		employeerepository.addEmployee(name,position,pay,on_board_date);
		return "emp/emp_list";
	}
	
	@RequestMapping(value="/toAddEmployee",method=RequestMethod.POST)
	public String toAddEmployee() {		
		return "emp/add_emp";
	}
	
	@RequestMapping(value="/toAlterEmployee",method=RequestMethod.GET)
	public String toAlterEmployee(@RequestParam String emp_id,Model model) {
		System.out.println("enter /toAlterEmployee controller");
		Employee employee = employeerepository.getEmployeeById(emp_id);
		model.addAttribute("employee",employee);
		return "emp/add_emp";
	}
}
