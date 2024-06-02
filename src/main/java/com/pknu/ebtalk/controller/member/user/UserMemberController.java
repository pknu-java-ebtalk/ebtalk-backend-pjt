package com.pknu.ebtalk.controller.member.user;

import com.pknu.ebtalk.dto.member.UserMemberDto;
import com.pknu.ebtalk.dto.study.StudyDto;
import com.pknu.ebtalk.service.member.user.UserMemberService;
import jakarta.validation.Valid;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.lang.reflect.Member;

@Log4j2
@Controller
@RequiredArgsConstructor
@RequestMapping("/member")
public class UserMemberController {

    private final UserMemberService userMemberService;

    // 회원가입
    @GetMapping(value={"/sign_up"})
    public String userSignUp(Model model) {
        log.info("[UserMemberController] userSignUp()");

        model.addAttribute("userMemberDto", new UserMemberDto());

        return "/html/member/user_sign_up";
    }

    // 회원가입 정보 받음
    @PostMapping("/sign_up_confirm")
    public String userSignUpConfirm(@Valid @ModelAttribute UserMemberDto userMemberDto, BindingResult result, Model model) {
        log.info("[UserMemberController] userSignUpConfirm()");

        if (result.hasErrors() || !userMemberDto.getPw().equals(userMemberDto.getPw_check())){
            return "/html/member/user_sign_up";
        }

        userMemberService.insertUserSignUpConfirm(userMemberDto);

        return "/html/member/user_sign_up_result";
    }


}
