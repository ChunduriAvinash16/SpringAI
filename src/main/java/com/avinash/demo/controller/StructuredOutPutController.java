package com.avinash.demo.controller;

import com.avinash.demo.advisor.TokenUsageAuditAdvisor;
import com.avinash.demo.model.CountryCities;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.converter.ListOutputConverter;
import org.springframework.ai.converter.MapOutputConverter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.Objects;


@RestController
@RequestMapping("/api/v1")
public class StructuredOutPutController {

    private ChatClient chatClient;

    public StructuredOutPutController(ChatClient.Builder chatClient) {
        this.chatClient = chatClient.build();
    }

    @GetMapping("/countries")
    public ResponseEntity<CountryCities> getCountries(@RequestParam("message") String message) {
        CountryCities entity = chatClient
                .prompt(message)
                .advisors(List.of(new SimpleLoggerAdvisor(), new TokenUsageAuditAdvisor()))
                .call()
                .entity(CountryCities.class);
        return ResponseEntity.ok(entity);
    }


    @GetMapping("/city-list")
    public ResponseEntity<List<String>> getCitiesList(@RequestParam("message") String message) {
        List<String> entity = chatClient
                .prompt(message)
                .advisors(List.of(new SimpleLoggerAdvisor(), new TokenUsageAuditAdvisor()))
                .call()
                .entity(new ListOutputConverter());
        return ResponseEntity.ok(entity);

    }

    @GetMapping("/city-map")
    public ResponseEntity<Map<String,Object>> getCitiesMap(@RequestParam("message") String message) {
        Map<String, Object> entity = chatClient
                .prompt(message)
                .advisors(List.of(new SimpleLoggerAdvisor(), new TokenUsageAuditAdvisor()))
                .call()
                .entity(new MapOutputConverter());
        return ResponseEntity.ok(entity);
    }
}
