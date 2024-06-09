package com.pknu.ebtalk.service.chatting;


import com.pknu.ebtalk.dto.chatting.ChatRoomDto;
import com.pknu.ebtalk.dto.chatting.ChatRoomUserDto;

import java.util.List;

public interface IChattingService {

    // 조건에 맞는 사용자 이름을 조회하는 메서드
    List<ChatRoomUserDto> selectChatName(ChatRoomUserDto param);

    // 새 채팅방을 생성하는 메서드
    void createChatRoom(ChatRoomDto chatRoom);

    // 채팅방에 사용자를 추가하는 메서드
    void addChatRoomUser(ChatRoomUserDto chatRoomUser);

}
