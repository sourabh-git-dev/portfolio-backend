package com.sourabh.portfolio.portfolio_backend.model;

import jakarta.persistence.*;

@Entity
@Table(name = "message")
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    private String message;  

    // constructor
    public Message() {
    }

    // Parameterized constructor
    public Message(String name, String email, String message) {
        this.name = name;
        this.email = email;
        this.message = message;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
