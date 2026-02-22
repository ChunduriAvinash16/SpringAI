package com.avinash.demo.controller;

import com.avinash.demo.advisor.TokenUsageAuditAdvisor;
import com.avinash.demo.model.CountryCities;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v1")
public class StructuredOutPutController {

    private ChatClient  chatClient;

    public StructuredOutPutController(ChatClient.Builder chatClient) {
        this.chatClient = chatClient.build();
    }

    @GetMapping("/countries")
    public ResponseEntity<CountryCities> getCountries(@RequestParam("message") String message){
        CountryCities entity = chatClient
                .prompt(message)
                .advisors(List.of(new SimpleLoggerAdvisor(), new TokenUsageAuditAdvisor()))
                .call()
                .entity(CountryCities.class);
        return ResponseEntity.ok(entity);
    }
}
