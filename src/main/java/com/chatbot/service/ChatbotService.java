package com.chatbot.service;

import com.chatbot.model.Message;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.Map;

@Service
public class ChatbotService {
    private final Map<String, String> predefinedResponses;

    public ChatbotService() {
        predefinedResponses = new HashMap<>();
        predefinedResponses.put("hello", "Hi there! How can I help you today?");
        predefinedResponses.put("how are you", "I'm just a chatbot, but I'm here to help you.");
        predefinedResponses.put("bye", "Goodbye! Take care.");
        predefinedResponses.put("sad", "I'm sorry to hear that. Do you want to talk about it?");
        predefinedResponses.put("anxious", "Anxiety can be tough. Try taking deep breaths or writing down your thoughts.");
        predefinedResponses.put("help", "You're not alone. I'm here to listen. You can also reach out to a trusted friend or professional.");
        predefinedResponses.put("thank", "You're welcome! I'm always here for you.");
    }

    public Message getResponse(String userMessage) {
        String response = "I'm here to listen. Tell me more."; // Default response
        String lowerCaseMessage = userMessage.toLowerCase(); // Convert to lowercase for better matching

        for (Map.Entry<String, String> entry : predefinedResponses.entrySet()) {
            if (lowerCaseMessage.contains(entry.getKey())) {
                response = entry.getValue();
                break;
            }
        }

        return new Message(userMessage, response);
    }
}
