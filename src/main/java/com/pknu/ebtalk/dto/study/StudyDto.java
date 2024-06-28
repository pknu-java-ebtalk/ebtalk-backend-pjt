package com.pknu.ebtalk.dto.study;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudyDto {
    private int no;
    private String user_id;
    private String title;
    private String started_at;
    private String finished_at;
    private int count;
    private String content;
    private int category_no;
    private String type;
    private int views;
    private String created_at;

    private String cur_date;
    private int count_member;

    private String approve_yn;

    private String name;
    private int edu_no;

    private int fav_no;
    private int fav_count;
}
