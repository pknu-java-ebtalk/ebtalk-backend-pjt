package com.pknu.ebtalk.service.member.user;

import com.pknu.ebtalk.dto.member.UserMemberDto;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;

public interface IUserMemberService {
    // 회원가입
    void insertUserSignUpConfirm(UserMemberDto userMemberDto);

    // 비밀번호 암호화
    String encodingPw(String pw);

    // 회원가입 - 아이디 중복 체크
    int selectUserSignInIdConfirm(String id);

    // 회원가입 - 비밀번호 확인
    boolean insertUserSignUpPwConfirm(UserMemberDto userMemberDto);

    // 로그인
    boolean selectUserSignIn(UserMemberDto userMemberDto);
    
    // 로그인 - 승인, 탈퇴여부 확인
    String selectUserSignInCondition(String id);
    
    // 로그인 - 세션 셋팅
    UserMemberDto selectUserSession(String id);

    // 로그인 - 아이디 찾기
    String selectUserFindId(HashMap<String, String> params);

    // 마이페이지 - 내 정보 가져오기
    UserMemberDto selectUserInfo(String id);

    // 마이페이지 - 정보 수정(프로필 사진)
    boolean updateUserInfoProfileImg(UserMemberDto userMemberDto);

    // 마이페이지 - 정보 수정(비밀번호)
    boolean updateUserInfoPw(UserMemberDto userMemberDto);

    // 마이페이지 - 정보 수정(전화번호)
    boolean updateUserInfoPhone(UserMemberDto userMemberDto);

    // 마이페이지 - 회원탈퇴
    boolean updateUserAccountDel(UserMemberDto userMemberDto);
}
