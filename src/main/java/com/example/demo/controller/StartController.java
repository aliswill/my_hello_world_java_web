package com.example.demo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.bean.Message;
import com.example.demo.repository.UserRepository;

@Controller
public class StartController {
	
	@Autowired
	UserRepository userrepository;
	
	@RequestMapping("/")
	public String getHome() {
		return "sunny/login";
	}
	
	//為了給重定向到登入後的頁面而建的方法
	@RequestMapping("/redirectToLogin")
	public String getToLogin() {
		return "main";
	}
	
	
	@GetMapping("/storeAccount") //登入後會跳轉到主頁(這個主頁不在主條目所以不會經常進入)
	public ResponseEntity<Object> storeAccount(HttpSession session){
		String user_account = 	(String) session.getAttribute("loginUser");
		//分清楚 何時存到Model 何時回傳ResponseEntity
		return new ResponseEntity<>(user_account, HttpStatus.OK);
	}
	
	
	@PostMapping("/login")
	public String login(@RequestParam("useraccount") String useraccount,@RequestParam("userpassword") String userpassword,Map<String,String> map,HttpSession session) {
		
		if(userrepository.checkAcPd(useraccount, userpassword)) {
			session.setAttribute("loginUser", useraccount);
			//map.put("useraccount", useraccount);
			//map.put("userpassword", userpassword);
			 
			//return "main";
			//return "/tologin";
			return "redirect:/redirectToLogin";//登入成功需防止使用者刷新頁面，使表單重複提交，需要重定向，但會丟失傳輸資訊
			//用重定向要如何保留我的session?這樣能通過攔截器嗎?
		}
		else {
			map.put("er_msg", "登入失敗，帳號或密碼錯誤");
			return "sunny/login";
		}
		


	}

	
}

