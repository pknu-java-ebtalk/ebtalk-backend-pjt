package com.pknu.ebtalk.dto.study;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudyRoomDto {
    private int no;
    private int STUDY_NO;
    private String user_id;
    private String content;
    private String created_at;

    private int M_CATEGORY_NO;
    private String type;
};
