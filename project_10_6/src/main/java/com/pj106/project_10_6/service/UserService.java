package com.pj106.project_10_6.service;

import com.pj106.project_10_6.dto.SecurityExceptionDto;
import com.pj106.project_10_6.dto.SignupRequestDto;
import com.pj106.project_10_6.entity.User;
import com.pj106.project_10_6.jwt.JwtUtil;
import com.pj106.project_10_6.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;


    private final PasswordEncoder passwordEncoder;


    // 회원가입
    @Transactional
    public SecurityExceptionDto signup(SignupRequestDto requestDto) {
        String loginId = requestDto.getLoginId();
        String password = passwordEncoder.encode(requestDto.getPassword());
        String email = requestDto.getEmail();
        String nickname = requestDto.getNickname();
        String profile = requestDto.getProfile();
        boolean isExistLoginId = userRepository.existsByLoginId(loginId);
        boolean isExistnickname = userRepository.existsBynickname(nickname);
        if (isExistLoginId) {
            throw new IllegalArgumentException("중복된 유저 아이디입니다.");
        } else if (isExistnickname) {
            throw new IllegalArgumentException("중복된 유저 닉네임입니다.");
        }

        User user = new User(loginId, password, email, nickname, profile);
        userRepository.save(user);
        return new SecurityExceptionDto("회원가입 성공", 200);
    }
}
