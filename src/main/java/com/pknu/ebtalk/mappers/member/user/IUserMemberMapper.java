package com.pknu.ebtalk.mappers.member.user;

import com.pknu.ebtalk.dto.member.UserMemberDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface IUserMemberMapper {
    // 아이디 중복 체크
    public int selectMemberSignUpIdCheck(String id);

    // 회원가입
    public int insertMemberSignUp(UserMemberDto userMemberDto);

    // 로그인
    public String selectMemberSignIn(String id, String pw);
}
