package com.pknu.ebtalk.controller.member.user;

import com.pknu.ebtalk.dto.member.UserMemberDto;
import com.pknu.ebtalk.dto.study.StudyDto;
import com.pknu.ebtalk.service.member.user.UserMemberService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Log4j2
@Controller
@RequiredArgsConstructor
@RequestMapping("/member")
public class UserMemberController {

    private final UserMemberService userMemberService;

    // 회원가입
    @GetMapping(value={"/sign_up"})
    public String userSignUp() {
        log.info("[UserMemberController] userSignUp()");

        String nextPage = "/html/member/user_sign_up";

        return nextPage;
    }

    // 회원가입 정보 받음
    @PostMapping("/sign_up_confirm")
    public String userSignUpConfirm(UserMemberDto userMemberDto, Model model) {
        log.info("[UserMemberController] userSignUpConfirm()");

        userMemberService.insertUserSignUpConfirm(userMemberDto);

        return "/html/member/user_sign_up_result";
    }


}
