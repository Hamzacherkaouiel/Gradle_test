package com.jenkins_app;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class RestClient {
    List<String> L;
    public  RestClient(){
        this.L=new ArrayList<>();
    }
    @GetMapping("/hello")
    public String helloWorld(){
        return "HELLO WORLD";
    }
    @PostMapping("/")
    public ResponseEntity<String> addItemes(@RequestBody String name){
        this.L.add(name);
        return ResponseEntity.status(201).body("CREATED ITEM");
    }
    @GetMapping("/")
    public  ResponseEntity<List<String>> getItemes(){
        return ResponseEntity.ok(this.L);
    }


}
