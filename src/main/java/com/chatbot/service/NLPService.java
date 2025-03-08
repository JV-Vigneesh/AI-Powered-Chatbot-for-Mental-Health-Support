package com.chatbot.service;

import org.springframework.stereotype.Service;

@Service
public class NLPService {

    public String getResponse(String message) {
        if (message.toLowerCase().contains("sad")) {
            return "I'm sorry you're feeling this way. Would you like to talk about it?";
        } else if (message.toLowerCase().contains("happy")) {
            return "That's great to hear! What made you happy today?";
        } else {
            return "I'm here to listen. Tell me more about how you're feeling.";
        }
    }
}
