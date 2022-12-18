package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.repository.UserRepository;



@Controller
public class RegisterController {

	@Autowired
	UserRepository userrepository;
	
	@RequestMapping("/toRegister")//進入註冊頁面
	public String toRegister() {
		return "/sunny/register";
	}
	
	@RequestMapping(value="/register",method=RequestMethod.POST)//註冊
	public String register(@RequestParam String useraccount,@RequestParam String userpassword,@RequestParam String email,Model model) {
		//註冊方法
		if(userrepository.accountExistYn(useraccount)) {
			model.addAttribute("msg", "此帳號已經存在");
		}else {
			userrepository.addUser(useraccount, userpassword, email, "一般權限");
			model.addAttribute("msg", "註冊成功!");
		}
		return "/sunny/register";
	}
}
