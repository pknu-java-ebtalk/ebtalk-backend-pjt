package com.pknu.ebtalk.dto.board;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BoardDto {
    private int no;
    private String user_id;
    private int b_category_no;
    private String title;
    private String content;
    private int views;
    private String created_at;
    private int boardHits;
    private String name;
}