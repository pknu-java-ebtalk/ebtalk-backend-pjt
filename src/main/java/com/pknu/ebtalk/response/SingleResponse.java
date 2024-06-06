package com.pknu.ebtalk.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SingleResponse<T> extends CommonResponse {
    private T data;     // 1 또는 0 값을 받음(하나의 단일값)
    
}
