package com.pknu.ebtalk.service.member.user;

import com.pknu.ebtalk.config.MemberConfig;
import com.pknu.ebtalk.dto.member.UserMemberDto;
import com.pknu.ebtalk.mappers.member.user.IUserMemberMapper;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

// 제대로 데이터 들어갔는지 결과 확인
@Log4j2
@Service
@RequiredArgsConstructor
public class UserMemberService implements IUserMemberService {

    private final IUserMemberMapper iUserMemberMapper;
    private final MemberConfig memberConfig;
    
    // 회원가입 - 아이디 중복 체크
    @Override
    public int selectUserSignInIdConfirm(String id){
        return  iUserMemberMapper.selectMemberSignUpIdCheck(id);
    }

    // 회원가입 - 비밀번호 확인
    @Override
    public boolean insertUserSignUpPwConfirm(UserMemberDto userMemberDto){
        return userMemberDto.getPw().equals(userMemberDto.getPw_check());
    }

    // 비밀번호 암호화
    @Override
    public String encodingPw(String pw){
        return memberConfig.passwordEncoder().encode(pw);
    }

    // 회원가입 정보 DB에 넣음
    @Override
    public void insertUserSignUpConfirm(UserMemberDto userMemberDto) {
        log.info("[UserMemberService] insertMemberSignUp()");

        userMemberDto.setPw(encodingPw(userMemberDto.getPw()));

        int result = iUserMemberMapper.insertMemberSignUp(userMemberDto);
        if (result > 0) {
            log.info("성공");
        } else{
            log.info("실패");
        }
    }

    // 로그인
    // 마이페이지 - 비밀번호 확인
    // 회원탈퇴 - 비밀번호 확인
    @Override
    public boolean selectUserSignIn(UserMemberDto userMemberDto) {
        log.info("[UserMemberService] selectMemberSignIn()");

        String id = userMemberDto.getId();
        String pw = userMemberDto.getPw();

        userMemberDto.setSign_in_pw_check(iUserMemberMapper.selectMemberSignIn(id));

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        if(userMemberDto.getSign_in_pw_check() == null || !passwordEncoder.matches(pw, userMemberDto.getSign_in_pw_check())){
            return false;
        }

        return true;
    }
    
    // 로그인 - 탈퇴, 승인여부 확인
    @Override
    public String selectUserSignInCondition(String id){
        log.info("[UserMemberService] selectMemberSignIn()");

        UserMemberDto userMemberDto = iUserMemberMapper.selectMemberSignInCondition(id);

        String approve_yn = userMemberDto.getApprove_yn();
        String delete_yn = userMemberDto.getDelete_yn();
        String admin_yn = userMemberDto.getAdmin_yn();

        if(delete_yn.equals("y")){  // 탈퇴한 계정일 경우
            return "d";
        } else if(approve_yn.equals("n")){  // 승인되지 않은 계정일 경우
            return "w";
        } else if(admin_yn.equals("y")){    // 관리자인 경우
            return "a";
        }

        return "y";
    }

   // 로그인 - 세션 셋팅
   @Override
   public UserMemberDto selectUserSession(String id){
        log.info("[UserMemberService] selectMemberSignIn()");
        return iUserMemberMapper.selectUserSession(id);
   }

   // 마이페이지 - 내 정보 확인
   @Override
   public UserMemberDto selectUserInfo(String id) {
        log.info("[UserMemberService] selectUserInfo()");

        return iUserMemberMapper.selectUserInfo(id);
   }

    // 마이페이지 - 프로필 사진 수정
    @Override
    public boolean updateUserInfoProfileImg(UserMemberDto userMemberDto) {
        log.info("[UserMemberService] updateInfoProfileImg()");

        return iUserMemberMapper.updateUserInfoProfileImg(userMemberDto) > 0;
    }
    
    // 마이페이지 - 비밀번호 수정
    @Override
    public boolean updateUserInfoPw(UserMemberDto userMemberDto) {
        log.info("[UserMemberService] updateInfoPw()");

        userMemberDto.setPw(encodingPw(userMemberDto.getPw()));

        return iUserMemberMapper.updateUserInfoPw(userMemberDto) > 0;
    }

    // 마이페이지 - 전화번호 수정
    @Override
    public boolean updateUserInfoPhone(UserMemberDto userMemberDto) {
        log.info("[UserMemberService] updateInfoPhone()");

        return iUserMemberMapper.updateUserInfoPhone(userMemberDto) > 0;
    }

    // 마이페이지 - 회원탈퇴
    @Override
    public boolean updateUserAccountDel(UserMemberDto userMemberDto){
        log.info("[UserMemberService] updateUserAccountDel()");

        String id = userMemberDto.getId();

        return iUserMemberMapper.updateUserAccountDel(id) > 0;
    }
}