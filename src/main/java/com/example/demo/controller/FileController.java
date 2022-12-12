package com.example.demo.controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;


import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class FileController {

	@GetMapping("/file_control")
	public String file() {
		return "file_control";
	}
	
	@ResponseBody
	@PostMapping("/downloadTxt")
	public ResponseEntity<byte[]> downloadTxt(HttpSession session,@RequestParam String filename) throws IOException{
		//@RequestBody(多用來接收json) 與 @RequestParam 須注意
		ServletContext servletcontext = session.getServletContext();
		String realPath = servletcontext.getRealPath("/");///static/download_files/"+filename+".txt
		//String realPath = FileController.class.getResourceAsStream("/main/resources/download_files"+filename+".txt");
		System.out.println(realPath);
		InputStream is = new FileInputStream(realPath);
		byte[] bytes = new byte[is.available()];
		is.read(bytes);//一次讀完文件中的bytes,還有一種做法是用迴圈讀到回傳-1為止
		MultiValueMap<String,String> header = new HttpHeaders();
		//為什麼這邊響應頭只需要設定幾個屬性?其他的內容像是編碼or其他響應頭資訊都不用設定?也沒看到HttpHeaders的空參建構子有先設定好
		header.set("Content-Disposition", "attachment;filename="+filename+".txt");
		HttpStatus statusCode = HttpStatus.OK;
		ResponseEntity<byte[]> responseentity = new ResponseEntity<>(bytes,header,statusCode);
		is.close();
		//System.out.println("接到控制器"+filename);
		return responseentity;
	}
}
