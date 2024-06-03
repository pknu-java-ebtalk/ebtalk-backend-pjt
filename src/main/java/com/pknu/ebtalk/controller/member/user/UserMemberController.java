package com.pknu.ebtalk.controller.member.user;

import com.pknu.ebtalk.dto.member.UserMemberDto;
import com.pknu.ebtalk.dto.study.StudyDto;
import com.pknu.ebtalk.mappers.member.user.IUserMemberMapper;
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
    private final IUserMemberMapper iUserMemberMapper;

    // 회원가입
    @GetMapping(value={"/sign_up"})
    public String userSignUp(Model model) {
        log.info("[UserMemberController] userSignUp()");

        model.addAttribute("userMemberDto", new UserMemberDto());

        return "/html/member/user_sign_up";
    }

    // 아이디 중복 체크
    @PostMapping("/checking_sign_up_id")
    public @ResponseBody int ajaxView(@RequestParam("id") String id){
        // 1 이상이면 중복 아이디 존재
        return iUserMemberMapper.selectMemberSignUpIdCheck(id);
    }

    // 회원가입 정보 받음
    @PostMapping("/sign_up_confirm")
    public String userSignUpConfirm(@Valid @ModelAttribute UserMemberDto userMemberDto, BindingResult result, Model model) {
        log.info("[UserMemberController] userSignUpConfirm()");

        int idCheck = iUserMemberMapper.selectMemberSignUpIdCheck(userMemberDto.getId());

        if(idCheck > 0){
            model.addAttribute("duplicatieMsg", "이미 사용 중인 아이디입니다.");
            return "/html/member/user_sign_up";
        }

        if (result.hasErrors() || !userMemberDto.getPw().equals(userMemberDto.getPw_check())){
            return "/html/member/user_sign_up";
        }

        userMemberService.insertUserSignUpConfirm(userMemberDto);

        return "/html/member/user_sign_up_result";
    }


}
