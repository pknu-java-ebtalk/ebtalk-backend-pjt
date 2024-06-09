package com.pknu.ebtalk.dto.chatting;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Getter
@Setter
public class ChatRoomUserDto {
    private int chat_room_id; // 채팅방 번호
    private String id; // 사용자 아이디
    private String name; // 사용자 이름
}
