package com.pknu.ebtalk.dto.chatting;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Getter
@Setter
public class ChatRoomUserDTO {
    private int CHAT_ROOM_ID; // 채팅방 번호
    private String USER_ID; // 사용자 아이디
}
