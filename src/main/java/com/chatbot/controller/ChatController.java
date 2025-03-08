package com.chatbot.controller;

import com.chatbot.model.ChatMessage;
import com.chatbot.repository.ChatRepository;
import com.chatbot.service.NLPService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/chat")
@CrossOrigin("*") // Allow frontend requests
public class ChatController {

    @Autowired
    private NLPService nlpService;

    @Autowired
    private ChatRepository chatRepository;

    @PostMapping("/message")
    public Map<String, String> chat(@RequestBody Map<String, String> request) {
        String userMessage = request.get("message");
        String botResponse = nlpService.getResponse(userMessage);

        // Save conversation to database
        ChatMessage chatMessage = new ChatMessage(userMessage, botResponse);
        chatRepository.save(chatMessage);

        Map<String, String> response = new HashMap<>();
        response.put("response", botResponse);
        return response;
    }
}
