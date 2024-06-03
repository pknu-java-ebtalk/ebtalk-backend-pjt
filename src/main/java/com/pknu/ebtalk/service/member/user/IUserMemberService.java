package com.pknu.ebtalk.service.member.user;

import com.pknu.ebtalk.dto.member.UserMemberDto;

public interface IUserMemberService {
    // 회원가입
    public void insertUserSignUpConfirm(UserMemberDto userMemberDto);

    // 회원가입 - 아이디 중복 체크
    public int selectUserSignInIdConfirm(String id);

    // 회원가입 - 비밀번호 확인
    public boolean insertUserSignUpPwConfirm(UserMemberDto userMemberDto);

    // 로그인
    public boolean selectUserSignIn(UserMemberDto userMemberDto);
}
