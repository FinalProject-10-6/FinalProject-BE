package com.pj106.project_10_6.service;

import com.pj106.project_10_6.dto.LoginRequestDto;
import com.pj106.project_10_6.dto.SecurityExceptionDto;
import com.pj106.project_10_6.dto.SignupRequestDto;
import com.pj106.project_10_6.entity.User;
import com.pj106.project_10_6.handler.GlobalExceptionHandler;
import com.pj106.project_10_6.jwt.JwtUtil;
import com.pj106.project_10_6.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
import java.util.Optional;
import java.util.regex.Pattern;


@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final JwtUtil jwtUtil;


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
        } else if (!(requestDto.getPassword().length() > 8 && requestDto.getPassword().length() < 16)) {
            return new GlobalExceptionHandler("패스워드 유효성검사 실패(길이)", HttpStatus.BAD_REQUEST);
        }
        // 닉네임 유효성 검사
        if (!(Pattern.matches("^[a-zA-Z0-9가-힣]*$", nickname))) {
            return new GlobalExceptionHandler("닉네임 유효성검사 실패(조합)", HttpStatus.BAD_REQUEST);
        } else if (!(nickname.length() > 2 && nickname.length() < 8)) {
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

    @Transactional(readOnly = true)
    public GlobalExceptionHandler login(LoginRequestDto loginRequestDto, HttpServletResponse response) {
        String username = loginRequestDto.getLoginId();
        String password = loginRequestDto.getPassword();

        // 사용자 확인
        User user = userRepository.findByLoginId(username).orElseThrow(
                () -> new IllegalArgumentException("등록된 사용자가 없습니다.")
        );

        // 비밀번호 확인
        if(!passwordEncoder.matches(password, user.getPassword())){
            throw  new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        response.addHeader(JwtUtil.AUTHORIZATION_HEADER, jwtUtil.createToken(user.getLoginId(), user.getRole()));
        return new GlobalExceptionHandler("로그인 완료", HttpStatus.OK);
    }
}

