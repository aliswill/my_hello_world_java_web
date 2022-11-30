package com.example.demo.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.bean.Employee;
import com.example.demo.repository.EmployeeRepository;

@RestController
public class EmployeeRepController {

	@Autowired
	Employee employee;
	
	@Autowired
	EmployeeRepository employeerepository;
	
	@ResponseBody //這個很重要!
	@RequestMapping("/getAllEmployeesRest")
	public ResponseEntity<Object> getAllEmployeesRest(Model model) {
		List<Employee> employees = employeerepository.getEmployeeList();
		//model.addAttribute("employees", employees);
		//把回傳的List<Employee>轉成json?
		
		return new ResponseEntity<>(employees, HttpStatus.OK);
		//研究一下 model存 是因為jsp跟thymeleaf可以調用
		//但我現在是要給ajax裡面寫方法 是不是不行用MODEL存查詢結果了 而是要return查詢結果?
	}
}
