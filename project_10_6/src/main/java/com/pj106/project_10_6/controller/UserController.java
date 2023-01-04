package com.pj106.project_10_6.controller;

import com.pj106.project_10_6.dto.LoginRequestDto;
import com.pj106.project_10_6.dto.LoginResponseDto;
import com.pj106.project_10_6.dto.MsgResponseDto;
import com.pj106.project_10_6.dto.SignupRequestDto;
import com.pj106.project_10_6.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;


    @PostMapping("/user/signup")
    public MsgResponseDto signup(@RequestBody @Valid SignupRequestDto signupRequestDto){
        return userService.signup(signupRequestDto);
    }

    @PostMapping("/user/login")
    public LoginResponseDto login(@RequestBody LoginRequestDto loginRequestDto, HttpServletResponse response){
        return userService.login(loginRequestDto, response);
    }

    @PostMapping("/user/{loginId}")
    public MsgResponseDto idCheck(@PathVariable String loginId){
        return userService.idCheck(loginId);
    }
}
