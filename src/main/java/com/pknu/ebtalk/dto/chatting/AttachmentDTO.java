package com.pknu.ebtalk.dto.chatting;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter
@Setter
public class AttachmentDTO {
    private int NO; // 첨부파일 번호
    private String FILE_PATH; // 파일 경로
    private String FILE_TYPE; // 파일 타입
    private LocalDateTime CREATE_AT; // 파일 업로드 시간
}
