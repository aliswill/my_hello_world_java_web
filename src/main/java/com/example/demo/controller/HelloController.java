package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller //透過thymeleaf 解析對應的html
public class HelloController {
	
	@RequestMapping("/hello3")
	public String hello3(){					
		return "hello3"; //會去找hello3.html
	}
	
	@RequestMapping("/hello4")
	@ResponseBody  //加上這個，等同回傳body，不解析頁面
	public String hello4(){					
		return "hello4";//回傳hello4 的文字
	}
}
