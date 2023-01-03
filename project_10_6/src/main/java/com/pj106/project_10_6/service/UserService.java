package com.pj106.project_10_6.service;

import com.pj106.project_10_6.dto.SecurityExceptionDto;
import com.pj106.project_10_6.dto.SignupRequestDto;
import com.pj106.project_10_6.entity.User;
import com.pj106.project_10_6.handler.GlobalExceptionHandler;
import com.pj106.project_10_6.jwt.JwtUtil;
import com.pj106.project_10_6.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.regex.Pattern;


@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;


    private final PasswordEncoder passwordEncoder;


    // 회원가입
    @Transactional
    public GlobalExceptionHandler signup(SignupRequestDto requestDto) {
        String loginId = requestDto.getLoginId();
        String password = passwordEncoder.encode(requestDto.getPassword());
        String email = requestDto.getEmail();
        String nickname = requestDto.getNickname();
        String profile = requestDto.getProfile();

        // 아이디 유효성 검사

        if (!(Pattern.matches("^[a-z0-9]*$", loginId))) {
            return new GlobalExceptionHandler("아이디 유효성검사 실패(조합)", HttpStatus.BAD_REQUEST);
        } else if (!(loginId.length() > 6 && loginId.length() < 10)) {
            return new GlobalExceptionHandler("아이디 유효성검사 실패(길이)", HttpStatus.BAD_REQUEST);
        }
        // 패스워드 유효성 검사
        if (!(Pattern.matches("^.(?=.*\\d)(?=.*[a-zA-Z])(?=.*[!@#$%^&+=]).*$", requestDto.getPassword()))) {
            return new GlobalExceptionHandler("패스워드 유효성검사 실패(조합)", HttpStatus.BAD_REQUEST);
        } else if (!(requestDto.getPassword().length() < 8 || requestDto.getPassword().length() > 16)) {
            return new GlobalExceptionHandler("패스워드 유효성검사 실패(길이)", HttpStatus.BAD_REQUEST);
        }
        // 닉네임 유효성 검사
        if (!(Pattern.matches("^[a-zA-Z0-9가-힣]*$", nickname))) {
            return new GlobalExceptionHandler("닉네임 유효성검사 실패(조합)", HttpStatus.BAD_REQUEST);
        } else if (nickname.length() < 2 || nickname.length() > 8) {
            return new GlobalExceptionHandler("닉네임 유효성검사 실패(길이)", HttpStatus.BAD_REQUEST);
        }

        boolean isExistLoginId = userRepository.existsByLoginId(loginId);
        boolean isExistnickname = userRepository.existsBynickname(nickname);
        if (isExistLoginId) {
            return new GlobalExceptionHandler("중복된 유저 아이디", HttpStatus.BAD_REQUEST);
        } else if (isExistnickname) {
            return new GlobalExceptionHandler("중복된 유저 닉네임", HttpStatus.BAD_REQUEST);
        }

        User user = new User(loginId, password, email, nickname, profile);
        userRepository.save(user);
        return new GlobalExceptionHandler("회원가입 성공", HttpStatus.OK);
    }
}

