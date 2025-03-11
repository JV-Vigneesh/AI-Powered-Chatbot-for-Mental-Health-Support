package com.chatbot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.web.bind.annotation.GetMapping;

@EntityScan("com.chatbot.model")
@SpringBootApplication
public class MentalHealthChatbotApplication {
	public static void main(String[] args) {
		SpringApplication.run(com.chatbot.MentalHealthChatbotApplication.class, args);
	}
	@GetMapping("/")
	public String home() {
		return "Chatbot is Running!";
	}
}
