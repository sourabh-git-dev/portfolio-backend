package com.sourabh.portfolio.portfolio_backend.controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/")
    public String home() {
        return "Backend is running 🚀";
    }

    @GetMapping("/ping")
    public String ping() {
        return "pong 🏓";
    }
}
