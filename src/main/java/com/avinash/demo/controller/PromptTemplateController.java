package com.avinash.demo.controller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("api/v1/")
public class PromptTemplateController {

    private ChatClient chatClient;
    PromptTemplateController(ChatClient chatClient) {
        this.chatClient = chatClient;
    }

    @Value("classpath:/promptTemplates/userPromptTemplate.st")
    Resource userPromptTemplate;


    @GetMapping("/email")
    public String getEmail(@RequestParam("customerName") String customerName,
                           @RequestParam("customerConcern") String customerConcern) {
        return chatClient.prompt()
                .system("""
                         You are a professional customer service assistant which helps in 
                         drafting an email response regarding the customerConcern which helps increase in 
                         productivity        
                        """)
                .user(promptUserSpec -> {
                    promptUserSpec.text(userPromptTemplate)
                            .param("customerName",customerName)
                            .param("customerConcern",customerConcern);
                }).call().content();
    }
}
