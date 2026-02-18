package com.avinash.demo.controller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/")
public class PromptStuffingController {

    private ChatClient chatClient;
    PromptStuffingController(ChatClient chatClient) {
        this.chatClient = chatClient;
    }

    @Value("classpath:/promptTemplates/stuffingTemplate.st")
    Resource systemPromptTemplate;


    @GetMapping("/promptStuffing")
    public String withPromptStuffing(@RequestParam("message") String message) {
        return chatClient.prompt()
                .system(systemPromptTemplate) //if the system is commented then defaultSystem
                .user(message)
                .call()
                .content();
    }
}
