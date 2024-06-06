package com.pknu.ebtalk.response;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public class MapResponse<K, V> extends CommonResponse {
    private Map<K, V> dataMap;  // Map 자료구조로 들어온 데이터를 처리할 때 사용

}
