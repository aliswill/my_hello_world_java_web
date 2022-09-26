package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.bean.Player;

@RestController
public class PlayerController {
	@Autowired
	Player player;
	
	@RequestMapping("/player")
	public Player checkPlayer() {
		return player;
	}
}
