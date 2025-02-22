package com.project.chatbox;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ChatService {

	private final RestTemplate restTemplate;

	public ChatService(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	public String getChatbotResponse(String message) {
		String chatbotUrl = "http://localhost:8081/chat";

		Map<String, String> requestBody = new HashMap<>();
		requestBody.put("message", message);

		@SuppressWarnings("rawtypes")
		ResponseEntity<Map> response = restTemplate.postForEntity(chatbotUrl, requestBody, Map.class);

		String chatbotResponse = (String) response.getBody().get("response");

		return chatbotResponse;
	}
}
