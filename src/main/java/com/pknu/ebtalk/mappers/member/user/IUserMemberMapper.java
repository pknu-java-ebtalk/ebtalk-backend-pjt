package com.pknu.ebtalk.mappers.member.user;

import com.pknu.ebtalk.dto.member.UserMemberDto;
import jakarta.servlet.http.HttpSession;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface IUserMemberMapper {
    // 아이디 중복 체크
    int selectMemberSignUpIdCheck(String id);

    // 회원가입
    int insertMemberSignUp(UserMemberDto userMemberDto);

    // 로그인 - id 동일한 db에서 pw 가져옴
    String selectMemberSignIn(String id);

    // 로그인 - 승인, 탈퇴여부 확인
    UserMemberDto selectMemberSignInCondition(String id);

    // 세션 확인
    String checkSession(HttpSession session);

    // 마이페이지 정보 읽어오기
    UserMemberDto selectUserInfo(String id);

    // 마이페이지 - 정보 수정(pw)
    int updateUserInfoPw(UserMemberDto userMemberDto);

    // 마이페이지 - 정보 수정(phone)
    int updateUserInfoPhone(UserMemberDto userMemberDto);

    // 마이페이지 - 정보 수정(profile_img)
    int updateUserInfoProfileImg(UserMemberDto userMemberDto);

    // 마이페이지 - 회원탈퇴
    int updateUserAccountDel(String id);
}
