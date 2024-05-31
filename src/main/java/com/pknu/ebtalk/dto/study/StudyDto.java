package com.pknu.ebtalk.dto.study;

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
    private int views;
    private String created_at;
}
