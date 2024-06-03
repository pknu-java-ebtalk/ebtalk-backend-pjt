package com.pknu.ebtalk.service.member.user;

import com.pknu.ebtalk.config.MemberConfig;
import com.pknu.ebtalk.dto.member.UserMemberDto;
import com.pknu.ebtalk.mappers.member.user.IUserMemberMapper;
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

    // 회원가입 정보 DB에 넣음
    @Override
    public void insertUserSignUpConfirm(UserMemberDto userMemberDto) {
        log.info("[UserMemberService] insertMemberSignUp()");

        // pw 암호화
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        userMemberDto.setPw(memberConfig.passwordEncoder().encode((userMemberDto.getPw())));

        int result = iUserMemberMapper.insertMemberSignUp(userMemberDto);
        if (result > 0) {
            log.info("성공");
        } else{
            log.info("실패");
        }
    }

    // 로그인
    @Override
    public boolean selectUserSignIn(UserMemberDto userMemberDto) {
        log.info("[UserMemberService] selectMemberSignIn()");

        String id = userMemberDto.getId();
        String pw = userMemberDto.getPw();

        userMemberDto.setSign_in_pw_check(iUserMemberMapper.selectMemberSignIn(id, pw));

        if(userMemberDto.getSign_in_pw_check() == null){
            return false;
        }

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        if(!passwordEncoder.matches(pw, userMemberDto.getSign_in_pw_check())){
            return false;
        }

        return true;
    }
}