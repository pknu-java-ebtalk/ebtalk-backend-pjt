package com.pknu.ebtalk.service.member.user;

import com.pknu.ebtalk.dto.member.UserMemberDto;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public interface IUserMemberService {
    // 회원가입
    void insertUserSignUpConfirm(UserMemberDto userMemberDto);

    // 회원가입 - 아이디 중복 체크
    int selectUserSignInIdConfirm(String id);

    // 회원가입 - 비밀번호 확인
    boolean insertUserSignUpPwConfirm(UserMemberDto userMemberDto);

    // 로그인
    boolean selectUserSignIn(UserMemberDto userMemberDto);

    // 마이페이지 내 정보 가져오기
    UserMemberDto selectUserInfo(String id);
}
