package com.pj106.project_10_6.dto;

import com.pj106.project_10_6.entity.UserRoleEnum;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
@Setter
public class SignupRequestDto {

    @NotBlank(message = "아이디를 입력하세요")
    @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*[0-9]).{6,10}$", message = "아이디는 영어, 숫자 6~10자리만 가능합니다")
    private String loginId;

    @NotBlank(message = "패스워드를 입력하세요")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&].{8,16}$", message = "비밀번호는 알파벳 대소문자와 숫자, 특수문자로 구성된 8~16자리여야 합니다.")
    private String password;

    @NotBlank(message = "닉네임을 입력하세요")
    private String nickname;

    @NotBlank(message = "이메일을 입력하세요")
    @Pattern(regexp = "\\w+@\\w+\\.\\w+(\\.\\w+)?", message = "이메일 형식이 아닙니다.")
    private String email;
}
