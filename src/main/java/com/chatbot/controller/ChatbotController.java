package com.chatbot.controller;

import com.chatbot.model.Message;
import com.chatbot.service.ChatbotService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/chat")
@CrossOrigin(origins = "*") // Allow frontend access
public class ChatbotController {
    private final ChatbotService chatbotService;

    public ChatbotController(ChatbotService chatbotService) {
        this.chatbotService = chatbotService;
    }

    @GetMapping(produces = "application/json")  // Ensure JSON response
    public Message chat(@RequestParam String message) {
        return chatbotService.getResponse(message);
    }

}
