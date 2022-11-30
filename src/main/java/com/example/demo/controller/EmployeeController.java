package com.example.demo.controller;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
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
	
	@RequestMapping(value="/toAlterEmployee/{emp_id}",method=RequestMethod.GET)
	public String toAlterEmployee(@PathVariable("emp_id") String emp_id,Model model) {
		//System.out.println("enter /toAlterEmployee controller");
		Employee employee = employeerepository.getEmployeeById(emp_id);
		model.addAttribute("employee",employee);
		//return "redirect:/toAddEmployee";
		return "emp/add_emp";
		//!注意!請求由ajax發來的話不能支持頁面跳轉
	}
	
	@RequestMapping(value="/deleteEmployee/{emp_id}",method=RequestMethod.GET)
	public String deleteEmployee(@PathVariable("emp_id") String emp_id) {
		employeerepository.deleteEmployeeById(emp_id);
		return "emp/emp_list";
	}
	
	
	@RequestMapping(value="/toAddEmployee",method=RequestMethod.POST)
	public String toAddEmployee() {		
		return "emp/add_emp";
	}
	
	@RequestMapping(value="/alterEmployee",method=RequestMethod.POST)
	public String alterEmployee(String id,String name,String position,Integer pay,Date on_board_date) {
		employeerepository.alterEmployee(id, name, position, pay, on_board_date);
		System.out.println("alterEmployee controller");
		return "emp/emp_list";
	}
}
