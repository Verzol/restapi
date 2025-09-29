package com.example.restapi.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "Welcome to REST API! Available endpoints:\n" +
               "POST /auth/register - Register new user\n" +
               "POST /auth/login - Login\n" +
               "GET /blogs - Get all blogs (requires authentication)\n" +
               "GET /h2 - H2 Database Console";
    }

    @GetMapping("/public/health")
    public String health() {
        return "API is running!";
    }
}