package com.universitymlproject.cryptopredictor.restcontroller.usercontroller;

import com.universitymlproject.cryptopredictor.model.userrelated.Jwt;
import com.universitymlproject.cryptopredictor.service.userrelatedservice.JwtService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/jwt")
public class JwtController {

    private JwtService jwtService;

    public JwtController(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @GetMapping("/{userId}")
    public Jwt getJwtByUserId(@PathVariable long userId){
        return jwtService.getJwtByUserId(userId);
    }

}
