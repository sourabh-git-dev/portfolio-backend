package com.sourabh.portfolio.portfolio_backend.controller;

import com.sourabh.portfolio.portfolio_backend.model.Message;
import com.sourabh.portfolio.portfolio_backend.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = {"http://127.0.0.1:5500", "*"}) 
@RequestMapping("/api/messages")
public class MessageController {

    private final MessageRepository messageRepository;

    @Autowired
    public MessageController(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @PostMapping
    public ResponseEntity<Message> saveMessage(@RequestBody Message message) {
        if (message.getName() == null || message.getEmail() == null || message.getMessage() == null) {
            return ResponseEntity.badRequest().build();
        }
        Message savedMessage = messageRepository.save(message);
        return ResponseEntity.ok(savedMessage);
    }

    @GetMapping
    public ResponseEntity<Iterable<Message>> getAllMessages() {
        return ResponseEntity.ok(messageRepository.findAll());
    }
}
