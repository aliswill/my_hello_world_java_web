package com.example.demo.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class StartController {

	@RequestMapping("/sunnyhome")
	public String getHome() {
		return "sunny/sunnyhome";
	}
	
	//為了給重定向到登入後的頁面而建的方法
	@RequestMapping("/ToLogin")
	public String getToLogin() {
		return "sunny/login";
	}
	
	@PostMapping("/afterlogin")
	public String login(@RequestParam("useraccount") String useraccount,@RequestParam("userpassword") String userpassword,Map<String,String> map,HttpSession session) {
		//Map<String,String> map = new HashMap<>();
		if(useraccount.equals("sunny")&& userpassword!=null) {
			session.setAttribute("loginUser", useraccount);
			map.put("useraccount", useraccount);
			map.put("userpassword", userpassword);
			return "sunny/login";
			//return "/afterlogin";
			//return "redirect:/ToLogin";//登入成功需防止使用者刷新頁面，使表單重複提交，需要重定向，但會丟失傳輸資訊
		}
		else {
			map.put("er_msg", "登入失敗");
			return "sunny/sunnyhome";
		}
		

	}

	
}


