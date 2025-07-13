package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @Value("${HOSTNAME}")
    private String podName;

    @GetMapping("/v1/test")
    public String helloV1() {
        return "Hello from pod: " + podName;
    }

    @GetMapping("/")
    public String hello() {
        return "Hello from Spring Boot!";
    }
}
