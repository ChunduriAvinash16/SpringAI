package com.avinash.demo.controller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ChatMemoryController {

    ChatClient chatClient;

    public ChatMemoryController(@Qualifier("chatMemoryExample") ChatClient chatClient) {
        this.chatClient = chatClient;
    }

    @GetMapping("/chatMemory")
    public String sayHello(@RequestParam("message") String message) {
        return chatClient
                .prompt(message)
                .call()
                .content();
    }
}
