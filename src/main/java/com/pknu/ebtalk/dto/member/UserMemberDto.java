package com.pknu.ebtalk.dto.member;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
//@NoArgsConstructor
public class UserMemberDto {
//    @NotBlank
    private String id;          // 사용자 아이디(사용자)

//    @NotBlank
    private int edu_no;         // 교육과정 번호(사용자)
    private int no;             // 교육과정 번호(교육훈련과정)
    private String type;        // 과목명(교육훈련과정)
    private String started_at;  // 시작일(교육훈련과정)
    private String finished_at; // 종료일(교육훈련과정)

//    @NotBlank
    private String pw;          // 비밀번호(사용자)
    private String pw_check;    // 비밀번호 확인

//    @NotBlank
    private String phone;       // 전화번호(사용자)

//    @NotBlank
    private String name;        // 이름(사용자)

    private String profile_img; // 프로필 사진(사용자)

    private String approve_yn;  // 승인 여부(사용자)
    private String delete_yn;   // 탈퇴 여부(사용자)
    private String create_at;   // 가입일(사용자)
}
