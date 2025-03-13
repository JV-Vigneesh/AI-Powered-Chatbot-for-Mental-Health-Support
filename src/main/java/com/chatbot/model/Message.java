package com.chatbot.model;

import jakarta.persistence.*;

@Entity
@Table(name = "messages")
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userMessage;
    private String botResponse;
    private String sentiment;  // Added sentiment field

    public Message(String userMessage, String botResponse, String sentiment) {
        this.userMessage = userMessage;
        this.botResponse = botResponse;
        this.sentiment = sentiment;
    }

    public String getUserMessage() {
        return userMessage;
    }

    public String getBotResponse() {
        return botResponse;
    }

    public String getSentiment() {
        return sentiment;
    }
}