package com.pknu.ebtalk.service.member.user;

import com.pknu.ebtalk.config.MemberConfig;
import com.pknu.ebtalk.dto.member.UserMemberDto;
import com.pknu.ebtalk.mappers.member.user.IUserMemberMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;

// 제대로 데이터 들어갔는지 결과 확인
@Log4j2
@Service
@RequiredArgsConstructor
public class UserMemberService implements IUserMemberService {

    private final IUserMemberMapper iUserMemberMapper;
    private final MemberConfig memberConfig;

//    @Override
//    public int selectIdCheck(String id)throws Exception{
//        return iUserMemberMapper.selectMemberSignUpIdCheck(id);
//    }

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
}
