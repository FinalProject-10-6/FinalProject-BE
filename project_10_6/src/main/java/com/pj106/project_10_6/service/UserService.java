package com.pj106.project_10_6.service;


import com.pj106.project_10_6.dto.MsgDto;
import com.pj106.project_10_6.dto.UserDto;
import com.pj106.project_10_6.entity.User;
import com.pj106.project_10_6.errorcode.CommonStatusCode;
import com.pj106.project_10_6.errorcode.UserStatusCode;
import com.pj106.project_10_6.exception.RestApiException;
import com.pj106.project_10_6.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public MsgDto.ResponseDto signup(UserDto.SignupRequestDto requestDto) {
        String password;

        // 아이디 중복검사
        if (userRepository.existsByLoginId(requestDto.loginId()))
            throw new RestApiException(UserStatusCode.OVERLAPPED_LOGINID);

        // 닉네임 중복검사
        if (userRepository.existsByNickname(requestDto.nickname()))
            throw new RestApiException(UserStatusCode.OVERLAPPED_NICKNAME);

        // 이메일 중복검사
        if (userRepository.existsByEmail(requestDto.email()))
            throw new RestApiException(UserStatusCode.OVERLAPPED_EMAIL);

        // 비밀번호 암호화
        password = passwordEncoder.encode(requestDto.password());

        // DB 저장(회원가입 성공)
        userRepository.save(new User(requestDto.loginId(), password, requestDto.email(), requestDto.nickname(), requestDto.profile()));
        return new MsgDto.ResponseDto(UserStatusCode.USER_SIGNUP_SUCCESS);
    }

    public MsgDto.ResponseDto login(UserDto.LoginRequestDto requestDto, HttpServletResponse httpServletResponse) {
        // id 가 틀림
        User user = userRepository.findByLoginId(requestDto.loginId()).orElseThrow(
                () -> new RestApiException(UserStatusCode.NO_USER)
        );

        // password 가 틀림
        if (!passwordEncoder.matches(requestDto.password(), user.getPassword())) {
            throw new RestApiException(UserStatusCode.WRONG_PASSWORD);
        }

        //토큰주고



        return new MsgDto.ResponseDto(UserStatusCode.USER_LOGIN_SUCCESS);
    }

}
