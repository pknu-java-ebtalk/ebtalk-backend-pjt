package com.pknu.ebtalk.dto.member;

import com.pknu.ebtalk.service.member.user.PwConfirmCheck;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.validator.constraints.Range;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;

@Getter
@Setter
@NoArgsConstructor
@PwConfirmCheck(pw = "pw", pw_check = "pw_check")
public class UserMemberDto {
    @NotBlank(message = "아이디를 입력해주세요.")
    private String id;          // 사용자 아이디(사용자)

    @NotNull
    @Range(min=1, max=3, message = "교육과정을 선택해주세요.")
    private int edu_no;         // 교육과정 번호(사용자)
    private int no;             // 교육과정 번호(교육훈련과정)
    private String type;        // 과목명(교육훈련과정)
    private String started_at;  // 시작일(교육훈련과정)
    private String finished_at; // 종료일(교육훈련과정)

    @NotBlank(message = "비밀번호를 입력해주세요.")
    private String pw;          // 비밀번호(사용자)
    private String pw_check;    // 비밀번호 확인

    @NotBlank(message = "전화번호를 입력해주세요.")
    private String phone;       // 전화번호(사용자)

    @NotBlank(message = "이름을 입력해주세요.")
    private String name;        // 이름(사용자)

    private String profile_img_path; // 프로필 사진 경로(사용자)
    private String profile_img; // 프로필 사진(사용자)

    private String approve_yn;  // 승인 여부(사용자)
    private String delete_yn;   // 탈퇴 여부(사용자)
    private String create_at;   // 가입일(사용자)

    private String admin_yn;    // 관리자 여부(사용자)

    // 로그인 시 필요한 필드
    private String sign_in_pw_check;

    // 이메일 인증 시 필요한 필드
    private JavaMailSender javaMailSender;
}