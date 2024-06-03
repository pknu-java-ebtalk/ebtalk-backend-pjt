package com.pknu.ebtalk.dto.chatting;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;


@Getter
@Setter

public class ChatRoomDTO {
    private int NO; // 채팅방 번호
    private String NAME; // 채팅방 이름
    private String TYPE; // 채팅방 타입 1:1, 1:N
    private LocalDateTime CREATE_AT; //채팅방 생성일
}
