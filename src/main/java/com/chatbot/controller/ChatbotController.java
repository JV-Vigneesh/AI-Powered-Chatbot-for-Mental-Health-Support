package com.chatbot.controller;

import com.chatbot.service.ChatbotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/chat")
@CrossOrigin(origins = "*") // Allows frontend access
public class ChatbotController {

    @Autowired
    private ChatbotService chatbotService;

    @PostMapping("/send")
    public Map<String, String> chat(@RequestBody Map<String, String> request) {
        String userId = request.getOrDefault("userId", "defaultUser"); // Ensure userId exists
        String userMessage = request.get("message"); // Extract message from JSON
        String botResponse = chatbotService.getResponse(userId, userMessage);

        // Return JSON response
        Map<String, String> response = new HashMap<>();
        response.put("botResponse", botResponse);
        return response;
    }
}
