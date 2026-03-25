package com.avinash.demo.controller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

//@RestController
//@RequestMapping("/api")
public class MultipleModels {
    private final ChatClient openAiChatClient;
    private final ChatClient ollamaChatClient;

    public MultipleModels(@Qualifier("openAiChatClient") ChatClient openAiChatClient,
                          @Qualifier("ollamaChatClient") ChatClient ollamaChatClient) {
        this.openAiChatClient = openAiChatClient;
        this.ollamaChatClient = ollamaChatClient;
    }

    @GetMapping("/openai/chat")
    public String openAIChat(@RequestParam("message") String message) {
        return openAiChatClient.prompt(message).call().content();
    }

    @GetMapping("/ollama/chat")
    public String ollamaChat(@RequestParam("message") String message) {
        return ollamaChatClient.prompt(message).call().content();
    }

    @GetMapping("/compare")
    public String compareModels(@RequestParam String message) {
        String ollamaResponse = ollamaChatClient
                .prompt(message)
                .call()
                .content();
        String openAiResponse = openAiChatClient
                .prompt(message)
                .call()
                .content();
        return "Ollama Response:\n" + ollamaResponse +
                "\n\nOpenAI Response:\n" + openAiResponse;
    }
}
