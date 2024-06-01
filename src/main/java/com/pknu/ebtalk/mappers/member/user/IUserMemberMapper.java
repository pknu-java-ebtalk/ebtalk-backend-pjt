package com.pknu.ebtalk.mappers.member.user;

import com.pknu.ebtalk.dto.member.UserMemberDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface IUserMemberMapper {
    // 회원가입
    public int insertMemberSignUp(UserMemberDto userMemberDto);
}
