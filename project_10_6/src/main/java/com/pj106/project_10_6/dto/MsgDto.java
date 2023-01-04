package com.pj106.project_10_6.dto;


import com.pj106.project_10_6.errorcode.StatusCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MsgDto {

    public record ResponseDto(int statusCode, String msg) {
        public ResponseDto(StatusCode statusCode) {
            this(statusCode.getStatusCode(), statusCode.getMsg());
        }
    }

    public record DataResponseDto(int statusCode, String msg, Object data) {
        public DataResponseDto(StatusCode statusCode, Object data) {
            this(statusCode.getStatusCode(), statusCode.getMsg(), data);
        }
    }

}
