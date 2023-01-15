package com.example.demo.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.demo.repository.UserRepository;

@Controller
public class UserController {
	
	@Autowired
	UserRepository userrepository;
	
	//private final ResourceLoader resourceloader = new ResourceLoader();
//	@RequestMapping(value="/user/management",method=RequestMethod.GET)
//	public String toUser(HttpSession session,Model model) {
//		String user_account = (String) session.getAttribute("loginUser");
//		String head_url = userrepository.getHeadUrl(user_account);
//		System.out.println(head_url);
//		//model.addAttribute("head_url",head_url);
//		String test_url ="file://D:/hello_world_upload/sunny/istockphoto-1290233518-170667a.jpg";
//		model.addAttribute("head_url",test_url);
//		return "/user/management";
//	}
	
	@RequestMapping(value="/user/management",method=RequestMethod.GET)
	public String toUser() {
		
		return "/user/management";
	}
	
	//https://juejin.cn/post/7111712403082969096 使用请求参数中的 HttpServletResponse 来将流写入响应。
	@RequestMapping(value="/user/showHead",method=RequestMethod.GET)
	public void showHead(HttpSession session,HttpServletRequest request,HttpServletResponse response) {
		response.setContentType("image/jpg");//圖檔都可以用嗎?
		String user_account = (String) session.getAttribute("loginUser");
		String head_url = userrepository.getHeadUrl(user_account);
		System.out.println(head_url);
		//File file = new File("D:\\hello_world_upload\\sunny\\cat.jpg");
		File file = new File(head_url);
		InputStream in;
		
		try {
			in= new FileInputStream(file);

			ServletOutputStream os =response.getOutputStream();
			byte[] b = new byte[1024];
		    while (in.read(b) != -1) {
	            os.write(b);
	        }
	        in.close();
	        os.flush();
	        os.close();
		} catch (FileNotFoundException e) {
	        e.printStackTrace();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}
	
	//https://juejin.cn/post/7111712403082969096 關於src
//	@RequestMapping(value="/user/showHead",method=RequestMethod.GET)
//	public ResponseEntity<Object> showUserHead(HttpSession session,Model model){
//		String user_account = (String) session.getAttribute("loginUser");
//		String head_url = userrepository.getHeadUrl(user_account);
//		try {
//			return ResponseEntity.ok(resourceloader.getResource("file://D:/hello_world_upload/sunny/istockphoto-1290233518-170667a.jpg"));
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			return ResponseEntity.notFound().build();
//		}
//	}
}
