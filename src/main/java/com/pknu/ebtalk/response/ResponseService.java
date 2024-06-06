//package com.pknu.ebtalk.response;
//
//import org.springframework.stereotype.Service;
//
//import java.util.Map;
//
//@Service
//public class ResponseService {
//    // 단일 데이터 처리
//    public <T> SingleResponse<T> getSingleResponse(T data) {
//        SingleResponse<T> singleResponse = new SingleResponse<>();
//        singleResponse.setData(data);
//        if (data == null) {
//            setFailResponse(singleResponse);
//
//        } else {
//            setSuccessResponse(singleResponse);
//
//        }
//        return singleResponse;
//
//    }
//
//    // Map 데이터 처리
//    public<K, V> MapResponse<K, V> getMapResponse(Map<K, V> dataMap) {
//        MapResponse<K, V> mapResponse = new MapResponse<>();
//        mapResponse.setDataMap(dataMap);
//
//        if(dataMap != null && dataMap.size() > 0 || dataMap != null && !dataMap.isEmpty()) {
//            setSuccessResponse(mapResponse);
//
//        } else {
//            setFailResponse(mapResponse);
//        }
//        return mapResponse;
//
//    }
//
//    private void setSuccessResponse(CommonResponse response) {
//        response.setSuccess(true);
////        response.setCode(CommonResult.SUCCESS.getCode());
//        response.setMessage(CommonResult.SUCCESS.getMessage());
//    }
//}
