package com.example.blog.service;

import com.example.blog.dto.LoginDto;
import com.example.blog.dto.RegisterDto;

public interface AuthService {
    String logIn(LoginDto loginDto);

    String register(RegisterDto registerDto);
}
