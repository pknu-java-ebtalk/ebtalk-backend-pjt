package com.pknu.ebtalk.dto.study;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudyRoomDto {
    private int no;
    private int study_no;
    private String user_id;
    private String content;
    private String created_at;

    private int m_category_no;
    private String type;
    private String name;
};
