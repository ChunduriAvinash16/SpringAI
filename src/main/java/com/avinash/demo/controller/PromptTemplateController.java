package com.avinash.demo.controller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/")
public class PromptTemplateController {

    private ChatClient chatClient;
    PromptTemplateController(ChatClient chatClient) {
        this.chatClient = chatClient;
    }

    String promptTemplate = """
             A customer name {customerName} sent the following concern {customerConcern}
             
             Write a polite and helpful email response addressing the issue.
             Maintain a professional tone and provide reassurance.
             
             Respond as if you are writing the email body only. Don't include subject and signature.
            """;


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
                    promptUserSpec.text(promptTemplate)
                            .param("customerName",customerName)
                            .param("customerConcern",customerConcern);
                }).call().content();
    }
}
