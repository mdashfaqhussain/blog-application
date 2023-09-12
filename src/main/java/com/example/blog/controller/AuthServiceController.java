package com.example.blog.controller;

import com.example.blog.dto.LoginDto;
import com.example.blog.dto.RegisterDto;
import com.example.blog.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthServiceController {

    private AuthService authService;

    public AuthServiceController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping(value = {"/login", "/signing"})
    public ResponseEntity<String> login(@RequestBody LoginDto loginDto) {
        String s = authService.logIn(loginDto);
        return new ResponseEntity<>(s, HttpStatus.OK);

    }

    @PostMapping(value = {"/register", "/signup"})
    public ResponseEntity<String> register(@PathVariable RegisterDto registerDto){
        String register = authService.register(registerDto);
        return new ResponseEntity<>(register, HttpStatus.CREATED);
    }
}
