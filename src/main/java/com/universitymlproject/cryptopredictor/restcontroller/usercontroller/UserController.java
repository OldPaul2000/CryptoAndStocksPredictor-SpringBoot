package com.universitymlproject.cryptopredictor.restcontroller.usercontroller;

import com.universitymlproject.cryptopredictor.dto.userrelated.LoginCredentialsDTOPost;
import com.universitymlproject.cryptopredictor.dto.userrelated.LoginResponseDTO;
import com.universitymlproject.cryptopredictor.dto.userrelated.UserDTOGet;
import com.universitymlproject.cryptopredictor.service.userrelatedservice.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private UserService userService;
    private AuthenticationManager authenticationManager;

    public UserController(UserService userService,
                          AuthenticationManager authenticationManager) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginCredentialsDTOPost loginCredentials){
        return ResponseEntity.ok(userService.login(loginCredentials, authenticationManager));
    }

    @GetMapping("/{userId}")
    public UserDTOGet getUserById(@PathVariable("userId") long userId){
        return userService.getUserById(userId);
    }

}
