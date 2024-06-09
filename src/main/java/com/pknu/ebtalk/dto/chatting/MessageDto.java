package com.pknu.ebtalk.dto.chatting;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.Date;

@Getter
@Setter
public class MessageDto {
    private int no; // 메시지 번호
    private String content; // 메시지 내용
    private String from_id; // 송신자 아이디
    private String to_id; // 수신자 아이디
    private int chat_room_no; // 채팅방 번호
    private int file_no; // 첨부파일 번호
    private String message_type; // 메시지 타입
    private Timestamp create_at; // 메시지 전송 시간
    private String read_yn; // 메시지 읽음 처리

    public String getMessageContent() {
        return this.content;
    }

    public void setMessageContent(String content){
        this.content = content;
    }


    public void setCREATE_AT(Date date) {
        this.create_at = (Timestamp) date;
    }
}
