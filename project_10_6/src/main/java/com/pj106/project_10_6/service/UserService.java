package com.pj106.project_10_6.service;

import com.pj106.project_10_6.dto.MsgResponseDto;
import com.pj106.project_10_6.dto.SignupRequestDto;
import com.pj106.project_10_6.jwt.JwtUtil;
import com.pj106.project_10_6.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.pj106.project_10_6.entity.User;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;


    @Transactional
    public MsgResponseDto signup(SignupRequestDto signupRequestDto){
        //받아온 유저네임과 패스워드를 변수에 저장
        String loginId = signupRequestDto.getLoginId();
        String nickname = signupRequestDto.getNickname();
        String password = passwordEncoder.encode(signupRequestDto.getPassword());
        String email = signupRequestDto.getEmail();

        //회원 중복 확인, 받아온 값이 유저레포지토리에 있는지 확인
        Optional<User> foundId = userRepository.findByLoginId(loginId);
        if (foundId.isPresent()) { //존재하는 것을 찾았다면 에러처리
            throw new IllegalArgumentException("이미 존재하는 ID 입니다.");
        }

        Optional<User> foundNickname = userRepository.findByNickname(nickname);
        if (foundNickname.isPresent()){ //존재하는 것을 찾았다면 에러처리
            throw new IllegalArgumentException("이미 존재하는 닉네임 입니다.");
        }

        Optional<User> foundEmail = userRepository.findByEmail(email);
        if (foundEmail.isPresent()){ //존재하는 것을 찾았다면 에러처리
            throw new IllegalArgumentException("이미 존재하는 이메일 입니다.");
        }

        User user = new User(loginId, nickname, password, email);
        userRepository.save(user);

        return new MsgResponseDto("회원가입 완료", HttpStatus.OK.value());

    }
    }

