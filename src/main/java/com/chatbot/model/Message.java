package com.chatbot.model;


import jakarta.persistence.*;

@Entity
@Table(name = "messages")
public class Message {
    private String userMessage;
    private String botResponse;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // Ensure ID generation is set
    private Long id;

    private String content;
    public Message(String userMessage, String botResponse) {
        this.userMessage = userMessage;
        this.botResponse = botResponse;
    }

    public String getUserMessage() {
        return userMessage;
    }

    public String getBotResponse() {
        return botResponse;
    }
}
