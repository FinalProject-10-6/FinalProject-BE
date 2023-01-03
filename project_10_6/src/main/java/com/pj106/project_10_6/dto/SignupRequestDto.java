package com.pj106.project_10_6.dto;

import com.pj106.project_10_6.entity.User;
import com.pj106.project_10_6.entity.UserRoleEnum;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignupRequestDto {

    private String loginId;
    private String password;
    private String email;
    private String nickname;
    private String profile = "";
    private UserRoleEnum role = UserRoleEnum.USER;

}
