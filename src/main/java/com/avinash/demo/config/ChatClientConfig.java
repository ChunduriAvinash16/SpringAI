package com.avinash.demo.config;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ChatClientConfig {

    @Bean
    ChatClient chatClient(ChatClient.Builder chatClientBuilder) {
        return chatClientBuilder.defaultSystem("""
                        You are in internal assistant to plan how to prepare for an interview aiming\s
                        for the Software Engineer Role Along with AI having more than 4 Years of Experience\s
                        If the user ask any thing outside this topic kindly inform them that you can only assist related\s
                        to the preparation.\s
                        """)
                //.defaultUser("What is Java")
                .build();
    }
}
