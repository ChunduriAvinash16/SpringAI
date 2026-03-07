package com.avinash.demo.controller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import static org.springframework.ai.chat.memory.ChatMemory.CONVERSATION_ID;

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

    @GetMapping("/chatMemoryuser")
    public String sayUser(@RequestHeader("username") String username,
            @RequestParam("message") String message) {
        return chatClient
                .prompt()
                .user(message)
                .advisors(advisorSpec -> advisorSpec.param(CONVERSATION_ID, username))
                .call()
                .content();
    }
}
