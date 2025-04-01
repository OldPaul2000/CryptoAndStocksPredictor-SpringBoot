package com.universitymlproject.cryptopredictor.restcontroller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/api/v1")
public class CsrfController {

    @GetMapping("/csrf")
    public ResponseEntity<String> getCsrf(){
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
