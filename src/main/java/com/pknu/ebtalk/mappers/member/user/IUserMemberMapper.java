package com.pknu.ebtalk.mappers.member.user;

import com.pknu.ebtalk.dto.member.UserMemberDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface IUserMemberMapper {
    // 아이디 중복 체크
    int selectMemberSignUpIdCheck(String id);

    // 회원가입
    int insertMemberSignUp(UserMemberDto userMemberDto);

    // 로그인
    String selectMemberSignIn(String id, String pw);

    // 마이페이지 정보 읽어오기
    UserMemberDto selectUserInfo(String id);

    // 마이페이지 - 정보 수정(pw)
    int updateUserInfoPw(UserMemberDto userMemberDto);

    // 마이페이지 - 정보 수정(phone)
    int updateUserInfoPhone(UserMemberDto userMemberDto);

    // 마이페이지 - 정보 수정(profile_img)
    int updateUserInfoProfileImg(UserMemberDto userMemberDto);
}
