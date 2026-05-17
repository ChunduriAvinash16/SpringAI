package com.avinash.demo.controller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.ai.chat.memory.ChatMemory.CONVERSATION_ID;

@RestController
@RequestMapping("/api/rag")
public class RagController {
    private ChatClient chatClient;
    private VectorStore vectorStore;

    public RagController(@Qualifier("chatMemoryExample") ChatClient chatClient, VectorStore vectorStore) {
        this.chatClient = chatClient;
        this.vectorStore = vectorStore;
    }

    @Value("classpath:/promptTemplates/systemRadomPromptTemplate.st")
    Resource promptTemplates;

    @Value("classpath:/promptTemplates/systemDocumentTemplate.st")
    Resource pdfTemplate;

    @GetMapping("/random/chat")
    public ResponseEntity<String> randomChat(@RequestHeader("username") String userName,
                                             @RequestParam("message") String message){
        System.out.println("From the random Chat");
        SearchRequest searchRequest = SearchRequest.builder()
                .query(message)
                .topK(3) //top 3 documents
                .similarityThreshold(0.5) //probability more than 50%
                .build();
        //querying the vector Store
        List<Document> similarDocs = vectorStore.similaritySearch(searchRequest);

        //Joining the Strings
        String similarContext = similarDocs.stream().map(Document::getText)
                .collect(Collectors.joining(System.lineSeparator()));

        //Calling the LLM Model
        String answer = chatClient.prompt().system(
                        promptSystemSpec -> promptSystemSpec.text(promptTemplates)
                                .param("documents", similarContext))
                .advisors(a -> a.param(CONVERSATION_ID, userName))
                .user(message)
                .call()
                .content();

        return ResponseEntity.ok(answer);
    }


    @GetMapping("/document/chat")
    public ResponseEntity<String> documentChat(@RequestHeader("username") String userName,
                                             @RequestParam("message") String message){

        SearchRequest searchRequest = SearchRequest.builder().query(message).topK(2)
                .similarityThreshold(0.5).build();
        List<Document> similarDocs = vectorStore.similaritySearch(searchRequest);
        String similarContext = similarDocs.stream().map(Document::getText)
                .collect(Collectors.joining(System.lineSeparator()));

        String answer = chatClient.prompt().system(
                        promptSystemSpec -> promptSystemSpec.text(pdfTemplate)
                                .param("documents", similarContext))
                .advisors(a -> a.param(CONVERSATION_ID, userName))
                .user(message)
                .call().content();
        return ResponseEntity.ok(answer);
    }
}
