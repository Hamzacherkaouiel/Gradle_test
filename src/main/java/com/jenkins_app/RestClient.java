package com.jenkins_app;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class RestClient {
    @GetMapping("/hello")
    public String helloWorld(){
        return "HELLO WORLD";
    }
}
