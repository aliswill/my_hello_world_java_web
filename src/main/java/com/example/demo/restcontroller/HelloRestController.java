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
	public ResponseEntity<String> hello(){					
		return new ResponseEntity<>("OK，我說了你好", HttpStatus.OK);
	}
	
	@RequestMapping("/hello2")
	public String hello2(){					
		return "hello222";//回傳文字到頁面上? => yes，因為類別註解是@RestController，@RestController 為 @Controller 與 @ResponseBody 的複合註解
	}
	

}
