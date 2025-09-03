package com.sourabh.portfolio.portfolio_backend.controller;

import com.sourabh.portfolio.portfolio_backend.model.message;
import com.sourabh.portfolio.portfolio_backend.repository.messagerepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = {"http://127.0.0.1:5500", "*"}) // allow only your frontend origin

// Allow frontend to connect (important)
@RequestMapping("/api/messages")
public class MessageController {

    @Autowired
    private messagerepository messageRepository;

    @PostMapping
    public message saveMessage(@RequestBody message message) {
        return messageRepository.save(message);
    }
}
