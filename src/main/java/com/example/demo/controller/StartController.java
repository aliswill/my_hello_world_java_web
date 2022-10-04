package com.example.demo.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class StartController {

	@RequestMapping("/sunnyhome")
	public String getHone() {
		return "sunny/sunnyhome";
	}
	
	@RequestMapping("/afterlogin")
	public String login(@RequestParam("useraccount") String useraccount,@RequestParam("userpassword") String userpassword,Map<String,String> map) {
		//Map<String,String> map = new HashMap<>();
		map.put("useraccount", useraccount);
		map.put("userpassword", userpassword);
		return "sunny/login";
	}

	
}
