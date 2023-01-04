package com.pj106.project_10_6.dto;

import lombok.Getter;

@Getter
public class LoginResponseDto {

    private String msg;
    private int statusCode;
    private String profile;
    private String nickname;

    public LoginResponseDto(String msg, int statusCode, String profile, String nickname) {
        this.msg = msg;
        this.statusCode = statusCode;
        this.profile = profile;
        this.nickname = nickname;
    }



}
