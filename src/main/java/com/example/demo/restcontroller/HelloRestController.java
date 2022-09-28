package com.example.demo.restcontroller;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class HelloRestController {

	@RequestMapping("/hello")
	@CrossOrigin
	public ResponseEntity<String> hello(){					
		return new ResponseEntity<>("OK，我說了你好", HttpStatus.OK);
	}
	
	@RequestMapping("/hello2")
	public String hello2(){					
		return "hello222";//回傳文字到頁面上?
	}
	
	@RequestMapping("/hello3")
	//@ResponseBody
	public String hello3(){					
		return "hello3";//
	}


	
}
