package com.pj106.project_10_6.dto;

import com.pj106.project_10_6.entity.UserRoleEnum;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class SignupRequestDto {

    @NotBlank(message = "아이디를 입력하세요")
    private String loginId;

    @NotBlank(message = "패스워드를 입력하세요")
    private String password;

    @NotBlank(message = "닉네임을 입력하세요")
    private String nickname;

    @NotBlank(message = "이메일을 입력하세요")
    private String email;
}
