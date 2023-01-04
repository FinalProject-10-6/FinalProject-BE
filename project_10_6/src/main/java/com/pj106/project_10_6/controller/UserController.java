package com.pj106.project_10_6.controller;

import com.pj106.project_10_6.dto.MsgResponseDto;
import com.pj106.project_10_6.dto.SignupRequestDto;
import com.pj106.project_10_6.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;


    @PostMapping("/user/signup")
    public MsgResponseDto signup(@RequestBody SignupRequestDto signupRequestDto){
        return userService.signup(signupRequestDto);
    }
}
