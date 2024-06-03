package com.pknu.ebtalk.service.chatting;

import com.pknu.ebtalk.dto.chatting.ChatRoomUserDTO;
import com.pknu.ebtalk.dto.chatting.MessageDTO;

import java.util.List;
import java.util.Map;

public interface ChattingService {
    // 특정 번호(NO)가 속한 채팅방 목록 조회
    List<ChatRoomUserDTO> selectRoomList(int NO);
    // 2개의 id가 동일한 채팅방을 공유하는지 확인
    int checkChattingNo(Map<String, Integer> map);
    // 새로운 채팅방 생성 메서드
    int createChattingRoom(Map<String, Integer> map);
    // 새로운 메시지를 삽입하는 메서드

    int insertMessage(MessageDTO msg);

    // 메시지 읽음 상태를 업데이트하는 메서드
    int updateReadFlag(Map<String, Object> paramMap);
    // 조건에 맞는 메시지 목록을 조회하는 메서드
    List<MessageDTO> selectMessageList(Map<String, Object> paramMap);
    // 조건에 맞는 사용자 목록을 조회하는 메서드
    List<ChatRoomUserDTO> selectTarget(Map<String, Object> map);
}
