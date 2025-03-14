package com.chatbot.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.*;

@Service
public class GeminiService {

    @Value("${gemini.api.key}") // Read API key from application.properties
    private String apiKey;

    private static final String API_URL = "https://generativelanguage.googleapis.com/v1/models/gemini-1.5-pro:generateContent?key=";

    private final RestTemplate restTemplate = new RestTemplate();

    public String getAIResponse(String userMessage) {
        String prompt =
                "You are an AI chatbot specialized in **mental health support**. " +
                        "You **ONLY** provide responses related to mental well-being, emotional support, and coping strategies. " +
                        "If a user asks about **anything unrelated** (e.g., programming, science, general knowledge), **politely refuse** " +
                        "and guide them back to mental health topics. Here is the user's message: \n\n" + userMessage;

        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("contents", List.of(
                Map.of("parts", List.of(Map.of("text", prompt)))
        ));

        // ✅ Set request headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(requestBody, headers);

        try {
            ResponseEntity<Map> response = restTemplate.exchange(API_URL + apiKey, HttpMethod.POST, entity, Map.class);

            if (response.getBody() != null && response.getBody().containsKey("candidates")) {
                List<Map<String, Object>> candidates = (List<Map<String, Object>>) response.getBody().get("candidates");
                if (!candidates.isEmpty()) {
                    Map<String, Object> firstCandidate = candidates.get(0);
                    Map<String, Object> content = (Map<String, Object>) firstCandidate.get("content");
                    List<Map<String, Object>> parts = (List<Map<String, Object>>) content.get("parts");
                    return (String) parts.get(0).get("text");
                }
            }
        } catch (Exception e) {
            System.err.println("❌ Gemini API Error: " + e.getMessage());
            return "❌ Error: Unable to connect to Gemini API.";
        }

        return "I'm here to listen. How can I help you?";
    }
}
