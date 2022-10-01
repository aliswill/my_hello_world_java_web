package com.example.demo.restcontroller;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

//@Controller回傳view頁面 RestController回傳json等，
@RestController //RestController，他就是Controller 加 @ResponseBody 的註解，也相當在裡面的方法都標了這兩個註解
@RequestMapping("/api")
public class HelloRestController {

	@RequestMapping("/hello")
	public ResponseEntity<String> hello(){					
		return new ResponseEntity<>("OK，我說了你好", HttpStatus.OK);
	}
	
	@RequestMapping("/hello2")
	@ResponseBody
	public String hello2(){					
		return "hello222";//回傳文字到頁面上? => yes，因為類別註解是@RestController，@RestController 為 @Controller 與 @ResponseBody 的複合註解
	}
	

	@RequestMapping("/hello3")
	public String hello3(){					
		return "hello3";//
	}

}
