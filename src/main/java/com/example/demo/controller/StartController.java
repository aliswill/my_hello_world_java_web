package com.example.demo.controller;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class StartController {

	@RequestMapping("/")
	public String getHone() {
		return "sunny/sunnyhome";
	}
	
	
}
