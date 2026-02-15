package com.avinash.demo.controller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class HelloController {

    ChatClient chatClient;

    public HelloController(ChatClient.Builder chatClientBuilder) {
        this.chatClient = chatClientBuilder.build();
    }

    @GetMapping("/chat")
    public String sayHello(@RequestParam("message") String message) {
        return chatClient
                .prompt(message)
                .call()
                .content();
    }

    @GetMapping("/chat/v1")
    public String replyToMessage(@RequestParam("message") String messsage) {
        return chatClient
                .prompt()
                .system("""
                        You are in internal assistant to plan how to prepare for an interview aiming\s
                        for the Software Engineer Role Along with AI having more than 4 Years of Experience\s
                        If the user ask any thing outside this topic kindly inform them that you can only assist related\s
                        to the preparation.\s
                        """)
                .user(messsage)
                .call()
                .content();
    }
}
