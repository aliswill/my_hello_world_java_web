package com.example.demo.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.bean.Message;
import com.example.demo.repository.MessageRepository;

@Controller
public class MessageController {

	@Autowired 
	MessageRepository messagerepository;
	
	@GetMapping("/ToMessage")
	public String toMessage() {
		return "/message/message_list";
	}
	
	@ResponseBody
	@GetMapping("/getMessages")//只有用戶自己發的才有刪除案鍵
	public ResponseEntity<Object> getMessages(Model model){
		List<Message> allMessage= messagerepository.getMessageList();
		//分清楚 何時存到Model 何時回傳ResponseEntity
		return new ResponseEntity<>(allMessage, HttpStatus.OK);
	}
	
	@PostMapping("/addMessage")
	public String addMessage(String nickname,String message,HttpSession session) {
		messagerepository.addMessge(nickname, message, session);
		return "redirect:/ToMessage";
	}
	
	
	@GetMapping("/message/delete/{message_id}")
	public String deleteMessages(@PathVariable Integer message_id){
		messagerepository.checkAccount(message_id, null);
		messagerepository.deleteMessageById(message_id);
		return "redirect:/ToMessage";
	}
}
