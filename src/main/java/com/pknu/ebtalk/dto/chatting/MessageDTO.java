package com.pknu.ebtalk.dto.chatting;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.Date;

@Getter
@Setter
public class MessageDTO {
    private int NO; // 메시지 번호
    private String CONTENT; // 메시지 내용
    private String FROM_ID; // 송신자 아이디
    private String TO_ID; // 수신자 아이디
    private int CHAT_ROOM_NO; // 채팅방 번호
    private int FILE_NO; // 첨부파일 번호
    private String MESSAGE_TYPE; // 메시지 타입
    private Timestamp CREATE_AT; // 메시지 전송 시간
    private String READ_YN; // 메시지 읽음 처리

    public String getMessageContent() {
        return this.CONTENT;
    }

    public void setMessageContent(String content){
        this.CONTENT = content;
    }


    public void setCREATE_AT(Date date) {
        this.CREATE_AT = (Timestamp) date;
    }
}
