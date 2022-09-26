package com.example.demo.bean;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
//@ConfigurationProperties(prefix="game")
public class Game {
	private String game_name;
	private String game_type;
	
	public Game(String game_name, String game_type) {
		super();
		this.game_name = game_name;
		this.game_type = game_type;
	}
	
	public Game() {
	}

	public String getGame_name() {
		return game_name;
	}

	public void setGame_name(String game_name) {
		this.game_name = game_name;
	}

	public String getGame_type() {
		return game_type;
	}

	public void setGame_type(String game_type) {
		this.game_type = game_type;
	};
	
	
	
}
