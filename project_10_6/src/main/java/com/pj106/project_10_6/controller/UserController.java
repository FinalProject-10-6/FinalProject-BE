package com.pj106.project_10_6.controller;

import com.pj106.project_10_6.dto.LoginRequestDto;
import com.pj106.project_10_6.dto.SecurityExceptionDto;
import com.pj106.project_10_6.dto.SignupRequestDto;
import com.pj106.project_10_6.handler.GlobalExceptionHandler;
import com.pj106.project_10_6.repository.UserRepository;
import com.pj106.project_10_6.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {

    private final UserRepository userRepository;

    private final UserService userService;


    @PostMapping("/signup")
    public GlobalExceptionHandler signup(@Valid @RequestBody SignupRequestDto requestDto) {
        return userService.signup(requestDto);
    }

    @PostMapping("/login")
    public GlobalExceptionHandler login(@Valid @RequestBody LoginRequestDto loginRequestDto, HttpServletResponse response) {
            return userService.login(loginRequestDto, response);
    }
}

