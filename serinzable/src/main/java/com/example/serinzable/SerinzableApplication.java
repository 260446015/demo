package com.example.serinzable;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class SerinzableApplication {

    public static void main(String[] args) {
        SpringApplication.run(SerinzableApplication.class, args);
    }

    @GetMapping("check")
    public ResponseEntity redirect(){
        return ResponseEntity.status(401).build();
    }

}
