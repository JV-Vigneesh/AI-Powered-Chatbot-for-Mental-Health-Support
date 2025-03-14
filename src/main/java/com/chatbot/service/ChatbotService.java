package com.chatbot.service;

import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class ChatbotService {

    private final Map<String, String> predefinedResponses;
    private final GeminiService geminiService;
    private final Map<String, List<String>> userContext; // Tracks conversation history

    private static final List<String> nonMentalHealthKeywords = List.of(
            "generate image", "show me a picture", "create an image",
            "programming", "technology", "history", "science", "math"
    );

    public ChatbotService(GeminiService geminiService) {
        this.geminiService = geminiService;
        this.userContext = new HashMap<>();
        predefinedResponses = new HashMap<>();

        // 🌟 Emotional Support (Extended)
        predefinedResponses.put("sad", "😞 I'm sorry you're feeling this way. You're not alone! Do you want to talk more about it?");
        predefinedResponses.put("happy", "😊 That's great to hear! What made you happy today?");
        predefinedResponses.put("stressed", "😟 Take a deep breath. Try to take things one step at a time. You got this! 💪");
        predefinedResponses.put("anxious", "😔 I understand. Have you tried deep breathing or meditation? 🧘");
        predefinedResponses.put("depressed", "💙 I'm here for you. It's okay to feel this way. Talking to someone you trust might help. 🗣️");
        predefinedResponses.put("angry", "😡 I see that you're upset. It's okay to express emotions. Want to vent? 🗯️");
        predefinedResponses.put("burnout", "💼 Feeling overwhelmed? Try taking small breaks and prioritizing tasks. ☕");
        predefinedResponses.put("lonely", "😔 You're not alone. Talking to a close friend or family member might help. 🫂");
        predefinedResponses.put("grateful", "🙏 Gratitude is wonderful! What are you grateful for today? 💖");
        predefinedResponses.put("frustrated", "😤 That sounds frustrating. Want to talk about it? 💬");
        predefinedResponses.put("excited", "🎉 That's awesome! What are you excited about? 🤩");
        predefinedResponses.put("bored", "🥱 Feeling bored? Perhaps try a new hobby or activity. Do you want some suggestions? 💡");
        predefinedResponses.put("confused", "🤔 I understand that feeling. Can you tell me more about what's confusing you? ❓");
        predefinedResponses.put("disappointed", "💔 I'm sorry to hear that. It's okay to be disappointed. Would you like to talk about what happened? 🗣️");
        predefinedResponses.put("hopeful", "✨ That's a wonderful feeling! What are you hopeful for? 🌈");
        predefinedResponses.put("insecure", "🛡️ Everyone feels insecure sometimes. Remember your strengths. What's on your mind? 💭");
        predefinedResponses.put("motivated", "🚀 That's fantastic! Keep that momentum going. What are you working towards? 🎯");
        predefinedResponses.put("peaceful", "😌 Ah, that sounds lovely. Enjoy the moment of peace. 🕊️");
        predefinedResponses.put("proud", "👏 You should be! What are you proud of? 🏆");
        predefinedResponses.put("relaxed", "🍹 Excellent! It's important to take time to relax. 🏖️");
        predefinedResponses.put("surprised", "😲 Wow, that's unexpected! Tell me more! 😮");
        predefinedResponses.put("tired", "😴 It sounds like you need some rest. Make sure you get some sleep. 🌙");
        predefinedResponses.put("worried", "😟 What's worrying you? Sometimes talking it out can help. 🗣️");
        predefinedResponses.put("guilty", "😔 It's tough to deal with guilt. Mistakes happen. Do you want to talk about it?");
        predefinedResponses.put("jealous", "😶 Jealousy is a natural feeling. It's important to focus on what makes you unique. Want to chat?");
        predefinedResponses.put("homesick", "🏡 Missing home can be hard. Is there something comforting from home that might help?");
        predefinedResponses.put("overwhelmed", "🤯 It's okay to feel overwhelmed. Let's break it down into smaller steps. What's the first thing we can address? 🪜");

        // 🌟 Greetings & General (Extended)
        predefinedResponses.put("hello", "👋 Hi there! How can I help you today?");
        predefinedResponses.put("hi", "Hey! 😊 How are you feeling?");
        predefinedResponses.put("hey", "Hey! How's your day going?");
        predefinedResponses.put("how are you", "I'm just a bot, but I'm here to listen. How are *you* feeling?");
        predefinedResponses.put("bye", "Goodbye! Take care and stay strong! 💙");
        predefinedResponses.put("thank you", "You're welcome! I'm always here for you. 😊");
        predefinedResponses.put("good morning", "☀️ Good morning! Wishing you a bright and happy day!");
        predefinedResponses.put("good afternoon", "🌤️ Good afternoon! Hope your day is going well.");
        predefinedResponses.put("good evening", "🌙 Good evening! How can I make your evening better?");
        predefinedResponses.put("good night", "😴 Good night! Sweet dreams.");
        predefinedResponses.put("what is your name?", "I'm a helpful AI here to chat with you.");
        predefinedResponses.put("tell me a joke", "Why don't scientists trust atoms? Because they make up everything! 😂");
        predefinedResponses.put("tell me a fact", "Did you know that honey never spoils? 🍯");

        // 🌟 Crisis Support (Extended)
        predefinedResponses.put("suicidal", "💔 I'm really sorry you're feeling this way. Please, talk to someone you trust. If it's an emergency, call 988.");
        predefinedResponses.put("self harm", "💙 You are important. You're not alone. Please reach out to a professional or a trusted person.");
        predefinedResponses.put("need help", "If you're in immediate distress, please call 988. I'm here to listen.");
        predefinedResponses.put("panic attack", "🫁 Breathe in slowly... hold... and breathe out. You're safe. Let’s take this one step at a time.");
        predefinedResponses.put("feeling hopeless", "💙 You're not alone. Sometimes it helps to talk to someone close to you or seek professional support.");
        predefinedResponses.put("i want to die", "💔 I'm really sorry you're feeling this way. You're not alone. Please reach out to someone who can help. Call 988.");

        // Default response
        predefinedResponses.put("default", "🤔 I see. Can you tell me more?");
    }

    public String getResponse(String userId, String userMessage) {
        updateContext(userId, userMessage);

        if (isNonMentalHealthQuery(userMessage)) {
            return "I'm here to support your mental health. If you're feeling stressed, anxious, or need someone to talk to, I'm here to help. 💙";
        }

        for (String key : predefinedResponses.keySet()) {
            if (userMessage.toLowerCase().contains(key)) {
                return predefinedResponses.get(key);
            }
        }

        return generateContextAwareResponse(userId, userMessage);
    }

    private boolean isNonMentalHealthQuery(String userMessage) {
        return nonMentalHealthKeywords.stream().anyMatch(userMessage.toLowerCase()::contains);
    }

    private void updateContext(String userId, String message) {
        userContext.computeIfAbsent(userId, k -> new ArrayList<>()).add(message);
    }

    private String generateContextAwareResponse(String userId, String userMessage) {
        return geminiService.getAIResponse(userMessage);
    }
}
