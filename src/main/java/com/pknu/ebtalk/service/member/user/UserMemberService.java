package com.pknu.ebtalk.service.member.user;

import com.pknu.ebtalk.config.MemberConfig;
import com.pknu.ebtalk.dto.member.UserMemberDto;
import com.pknu.ebtalk.mappers.member.user.IUserMemberMapper;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

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

        userMemberDto.setSign_in_pw_check(iUserMemberMapper.selectMemberSignIn(id, pw));

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        if(userMemberDto.getSign_in_pw_check() == null || !passwordEncoder.matches(pw, userMemberDto.getSign_in_pw_check())){
            return false;
        }

        return true;
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

        int result = iUserMemberMapper.updateUserInfoProfileImg(userMemberDto);

        return result > 0;
    }
    
    // 마이페이지 - 비밀번호 수정
    @Override
    public boolean updateUserInfoPw(UserMemberDto userMemberDto) {
        log.info("[UserMemberService] updateInfoPw()");

        userMemberDto.setPw(encodingPw(userMemberDto.getPw()));

        int result = iUserMemberMapper.updateUserInfoPw(userMemberDto);

        return result > 0;
    }

    // 마이페이지 - 전화번호 수정
    @Override
    public boolean updateUserInfoPhone(UserMemberDto userMemberDto) {
        log.info("[UserMemberService] updateInfoPhone()");

        int result = iUserMemberMapper.updateUserInfoPhone(userMemberDto);

        return result > 0;
    }
}