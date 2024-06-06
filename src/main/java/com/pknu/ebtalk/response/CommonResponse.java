package com.pknu.ebtalk.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommonResponse {
    private boolean success;    // 성공 & 실패 보내기(True/False)
    private int code;
    private String message;     // 성공 & 실패시 프론트에 보낼 메세지

}
