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
import com.example.demo.bean.Sub_message;
import com.example.demo.repository.MessageLikeRepository;
import com.example.demo.repository.MessageRepository;

@Controller
public class MessageController {

	@Autowired 
	MessageRepository messagerepository;
	
	@Autowired 
	MessageLikeRepository messagelikerepository;
	
	@GetMapping("/ToMessage")
	public String toMessage() {
		return "/message/message_list";
	}
	
//	@GetMapping("/message/like/{message_id}")
//	public String changeLike(HttpSession session,@PathVariable String message_id) {
//		String user_account = (String) session.getAttribute("loginUser");
//		messagelikerepository.changeLike(user_account, message_id);
//		return "redirect:/ToMessage";
//	}
	
	@ResponseBody //按讚然後返回讚數
	@GetMapping("/message/getlike/{message_id}")
	public ResponseEntity<Object> getLike(HttpSession session,@PathVariable String message_id) {
		String user_account = (String) session.getAttribute("loginUser");
		messagelikerepository.changeLike(user_account, message_id);
		int like_num = messagelikerepository.getLikeNum(message_id);
		boolean already_like_yn = messagelikerepository.alreadyLikeYn(user_account, message_id);
		return new ResponseEntity<>(like_num+","+already_like_yn, HttpStatus.OK);
	}
	
	
	
//	@ResponseBody
//	@GetMapping("/message/likenum/{message_id}")//這樣可能不行?
//	public ResponseEntity<Object> getLikeNum(@PathVariable String message_id) {
//		int like_num = 0;
//		like_num = messagelikerepository.getLikeNum(message_id);
//		return new ResponseEntity<>(like_num, HttpStatus.OK);
//	}
	
	@ResponseBody
	@GetMapping("/getMessages")//只有用戶自己發的才有刪除案鍵 
	public ResponseEntity<Object> getMessages(Model model,HttpSession session){
		String user_account = (String) session.getAttribute("loginUser");
		List<Message> allMessage= messagerepository.getMessageList(user_account);
		//分清楚 何時存到Model 何時回傳ResponseEntity
		return new ResponseEntity<>(allMessage, HttpStatus.OK);
	}
	
	@PostMapping("/addMessage")
	public String addMessage(String nickname,String message,HttpSession session) {
		message = message.replace("\r\n", "<br/>").replace("\n", "<br/>");//注意!取代不會直接改變原字串
		messagerepository.addMessge(nickname, message, session);
		return "redirect:/ToMessage";
	}
	
	@PostMapping("/addSubMessage/{message_id}")
	public String addSubMessage(String nickname,String message,HttpSession session,@PathVariable Integer message_id) {
		message = message.replace("\r\n", "<br/>").replace("\n", "<br/>");
		messagerepository.addSubMessge(nickname, message, session, message_id);
		return "redirect:/ToMessage";
	}
	
	@GetMapping("/message/delete/{message_id}")
	public String deleteMessages(@PathVariable Integer message_id){
		messagerepository.checkAccount(message_id, null);
		messagerepository.deleteMessageById(message_id);
		return "redirect:/ToMessage";
	}
	
	@ResponseBody
	@GetMapping("/message/getreply/{message_id}")
	public ResponseEntity<Object> getSubMessages(@PathVariable Integer message_id){
		List<Sub_message> allMessage = messagerepository.getSubMessageList(message_id);
		return new ResponseEntity<>(allMessage, HttpStatus.OK);
	}
}
