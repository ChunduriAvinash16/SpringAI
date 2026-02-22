package com.avinash.demo.config;

import com.avinash.demo.advisor.TokenUsageAuditAdvisor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.ai.chat.prompt.ChatOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class ChatClientConfig {

    @Bean
    ChatClient chatClient(ChatClient.Builder chatClientBuilder) {
        ChatOptions chatOptions = ChatOptions.builder().model("llama3.2:1b")
                .maxTokens(100).temperature(0.8).build();
        return chatClientBuilder
                .defaultOptions(chatOptions)
                .defaultAdvisors(List.of(new  SimpleLoggerAdvisor(), new TokenUsageAuditAdvisor()))
//                .defaultAdvisors(new TokenUsageAuditAdvisor())
//                .defaultAdvisors(new SimpleLoggerAdvisor())
                .defaultSystem("""
                        You are in internal assistant to plan how to prepare for an interview aiming\s
                        for the Software Engineer Role Along with AI having more than 4 Years of Experience\s
                        If the user ask any thing outside this topic kindly inform them that you can only assist related\s
                        to the preparation.\s
                        """)
                //.defaultUser("What is Java")
                .build();
    }
}
