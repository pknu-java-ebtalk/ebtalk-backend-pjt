package com.pknu.ebtalk.dto.chatting;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;


@Getter
@Setter

public class ChatRoomDto {
    private int no; // 채팅방 번호
    private String name; // 채팅방 이름
    private String type; // 채팅방 타입 1:1, 1:N
    private LocalDateTime create_at; //채팅방 생성일

    public ChatRoomDto(String name, String type){
        this.name = name;
        this.type = type;
        this.create_at = LocalDateTime.now();
    }
}
