package com.pknu.ebtalk.service.member.user;

import com.pknu.ebtalk.config.MemberConfig;
import com.pknu.ebtalk.dto.member.UserMemberDto;
import com.pknu.ebtalk.mappers.member.user.IUserMemberMapper;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.Duration;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Random;

// 제대로 데이터 들어갔는지 결과 확인
@Log4j2
@Service
@RequiredArgsConstructor
public class UserMemberService implements IUserMemberService {

    private final IUserMemberMapper iUserMemberMapper;
    private final MemberConfig memberConfig;
    private final JavaMailSender mailSender;

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
        log.info("[UserMemberService] insertUserSignUpConfirm()");

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
        log.info("[UserMemberService] selectUserSignInCondition()");

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
        log.info("[UserMemberService] selectUserSession()");

        return iUserMemberMapper.selectUserSession(id);
    }

    // 로그인 - 아이디 찾기
    @Override
    public String selectUserFindId(HashMap<String, String> params){
        log.info("[UserMemberService] selectUserFindId()");

        return iUserMemberMapper.selectUserFindId(params);
    }

    // 로그인 - 비밀번호 확인
    @Override
    public boolean selectUserFindPwCheck(UserMemberDto userMemberDto){
        log.info("[UserMemberService] selectUserFindPwCheck()");

        return iUserMemberMapper.selectUserResetPwCheck(userMemberDto) > 0;
    }

    // --- 로그인 - 비밀번호 재설정 - 인증번호(이메일) 시작 --- /
    // 임의의 6자리 양수 반환
    public void makeRandomNum(HttpSession session){
        log.info("[UserMemberService] makeRandomNum()");

        Random r = new Random();
        String randomNum = "";
        for(int i=0; i<6; i++){
            randomNum += Integer.toString(r.nextInt(10));
        }

        session.setAttribute("randomNum", randomNum);
    }

    // 메일 내용 작성
    public void joinEmail(HttpSession session){
        log.info("[UserMemberService] joinEmail()");

        makeRandomNum(session);
        String setFrom = "이메일";    // 보내는 이메일(내 이메일 주소)
        String toMail = String.valueOf(session.getAttribute("id"));
        String title = "[이비톡] 비밀번호 재설정 인증 이메일입니다.";
        String content =
                "인증번호는 \"" + session.getAttribute("randomNum") + "\" 입니다." + "<br>" +
                "인증번호를 알맞게 입력하면 비밀번호 재설정이 가능합니다.";

        mailSend(setFrom, toMail, title, content);
    }

    // 메일 전송
    public void mailSend(String setFrom, String toMail, String title, String content){

        log.info("[UserMemberService] mailSend()");

        MimeMessage message = mailSender.createMimeMessage();

        try{
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "utf-8");
            helper.setFrom(setFrom);
            helper.setTo(toMail);
            helper.setSubject(title);
            helper.setText(content, true);
            mailSender.send(message);

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    // 제한 시간 시작 시간
    @Override
    public void timeCalculate(HttpSession session){
        log.info("[UserMemberService] timeCalculate()");

        LocalTime now = LocalTime.now();
        LocalTime expiryTime = now.plusMinutes(3);

        session.setAttribute("expiryTime", expiryTime);
    }
    
    // 제한 시간(3분) 계산
    public long getRemainingTime(HttpSession session){
        log.info("[UserMemberService] getRemainingTime()");
        LocalTime now = LocalTime.now();
        LocalTime expiryTime = (LocalTime) session.getAttribute("expiryTime");

        if (expiryTime == null) {
            return -1; // 제한 시간이 설정되지 않았음을 나타냄
        }

        Duration duration = Duration.between(now, expiryTime);

        return duration.getSeconds();
    }
    // --- 로그인 - 비밀번호 재설정 - 인증번호(이메일) 끝 --- /

    // 로그인 - 비밀번호 재설정
    @Override
    public boolean updateUserResetPw(String id, String pw){
        log.info("[UserMemberService] updateUserResetPw()");

        pw = encodingPw(pw);

        HashMap<String, String> params = new HashMap<>();

        params.put("id", id);
        params.put("pw", pw);

        return iUserMemberMapper.updateUserResetPw(params) > 0;
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