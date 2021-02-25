package com.example;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

    @GetMapping("/hello")
    public String getHello() {
        return "GET HELLO";
    }

    @PostMapping("/hello")
    public String postHello() {
        return "POST HELLO";
    }
}
