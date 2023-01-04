package com.pj106.project_10_6.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor // 파라미터가 없는 기본 생성자 생성
@Builder // 해당 클래스에 자동으로 빌더 추가
public class MsgResponseDto {
    private String msg;
    private int statusCode;

    public MsgResponseDto (String msg, int statusCode) {
        this.msg = msg;
        this.statusCode = statusCode;
    }
}