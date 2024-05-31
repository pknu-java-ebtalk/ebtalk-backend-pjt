package com.pknu.ebtalk.service.member.user;

import com.pknu.ebtalk.dto.member.UserMemberDto;

public interface IUserMemberService {
    // 회원가입
    public void insertUserSignUpConfirm(UserMemberDto userMemberDto);
}
