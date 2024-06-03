package com.pknu.ebtalk.controller.member.user;

import com.pknu.ebtalk.dto.member.UserMemberDto;
import com.pknu.ebtalk.service.member.user.UserMemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Log4j2
@Controller
@RequiredArgsConstructor
@RequestMapping("/member")
public class UserMemberController {

    private final UserMemberService userMemberService;

    // 회원가입 페이지
    @GetMapping(value={"/sign_up"})
    public String userSignUp(Model model) {
        log.info("[UserMemberController] userSignUp()");

        model.addAttribute("userMemberDto", new UserMemberDto());

        return "/html/member/user_sign_up";
    }

    // 아이디 중복 체크
    @PostMapping("/checking_sign_up_id")
    public @ResponseBody int userSignUpIdConfirm(@RequestParam("id") String id){
        // 1 이상이면 중복 아이디 존재
        return userMemberService.selectUserSignInIdConfirm(id);
    }

    // 회원가입 정보 받음
    @PostMapping("/sign_up_confirm")
    public String userSignUpConfirm(@Valid @ModelAttribute UserMemberDto userMemberDto, BindingResult result) {
        log.info("[UserMemberController] userSignUpConfirm()");

        if (result.hasErrors() || !userMemberService.insertUserSignUpPwConfirm(userMemberDto)){
            return "/html/member/user_sign_up";
        }

        userMemberService.insertUserSignUpConfirm(userMemberDto);

        return "/html/member/user_sign_up_result";
    }

    // 로그인 페이지
    @GetMapping("/sign_in")
    public String userSignIn() {
        log.info("[UserMemberController] userSignIn()");

        return "/html/member/user_sign_in";
    }

    // 로그인 정보 체크
    @PostMapping("/sign_in_check")
    public String userSignInCheck(UserMemberDto userMemberDto, @RequestParam("id") String id, @RequestParam("pw") String pw) {
        log.info("[UserMemberController] userSignIn()");

        if(!userMemberService.selectUserSignIn(userMemberDto)){
            return "/html/member/user_sign_in";
        }

        return "/html/home/home";
    }
}