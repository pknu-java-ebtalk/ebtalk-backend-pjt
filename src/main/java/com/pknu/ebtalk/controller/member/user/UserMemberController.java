package com.pknu.ebtalk.controller.member.user;

import com.pknu.ebtalk.dto.member.UserMemberDto;
import com.pknu.ebtalk.service.member.user.IUserMemberService;
import com.pknu.ebtalk.service.member.user.UserMemberService;
import jakarta.servlet.http.HttpSession;
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
    @GetMapping(value = {"/sign_in", "/", ""})
    public String userSignIn() {
        log.info("[UserMemberController] userSignIn()");

        return "/html/member/user_sign_in";
    }

    // 로그인 정보 체크
    @PostMapping("/sign_in_check")
    public @ResponseBody String userSignInCheck(HttpSession session, UserMemberDto userMemberDto, @RequestParam("id") String id) {
        log.info("[UserMemberController] userSignInCheck()");

        if(!userMemberService.selectUserSignIn(userMemberDto)){
            return "n";
        }

        session.setAttribute("id", id);

        return userMemberService.selectUserSignInCondition(userMemberDto.getId());
    }

    // 로그아웃
    @GetMapping("/log_out")
    public String userLogOut(HttpSession session) {
        log.info("[UserMemberController] userLogOut()");

        // 세션 삭제
        session.invalidate();

        return "redirect:/member/sign_in";
    }

    // 마이페이지 - 비밀번호 입력
    @GetMapping("/mypage_pw_input")
    public String userMyPage(HttpSession session) {
        log.info("[UserMemberController] userMyPage()");

        if(session.getAttribute("id") == null){
            return "redirect:/member/sign_in";
        }

        return "/html/member/user_info_pw_check";
    }

    // 마이페이지 - 비밀번호 확인
    @PostMapping("/mypage_pw_check")
    public String userMyPageCheck(HttpSession session, UserMemberDto userMemberDto, Model model) {
        log.info("[UserMemberController] userMyPageCheck()");

        userMemberDto.setId(String.valueOf(session.getAttribute("id")));

        if(!userMemberService.selectUserSignIn(userMemberDto)){
            model.addAttribute("error", true);
            return "/html/member/user_info_pw_check";
        }

        return "redirect:/member/mypage";
    }

    // 마이페이지 - 내 정보 확인
    @GetMapping("/mypage")
    public String userMyPage(Model model, HttpSession session) {
        log.info("[UserMemberController] userMyPage()");

        if(session.getAttribute("id") == null){
            return "redirect:/member/sign_in";
        }

        UserMemberDto userMemberDto = userMemberService.selectUserInfo(String.valueOf(session.getAttribute("id")));
        model.addAttribute("userMemberDto", userMemberDto);
        return "/html/member/user_info_view";
    }
    
    // 마이페이지 - 내 정보 수정
    @GetMapping("/mypage_change")
    public String userMyPageChange(Model model, HttpSession session) {
        log.info("[UserMemberController] userMyPageChange()");

        if(session.getAttribute("id") == null){
            return "redirect:/member/sign_in";
        }

        UserMemberDto userMemberDto = userMemberService.selectUserInfo(String.valueOf(session.getAttribute("id")));
        model.addAttribute("userMemberDto", userMemberDto);
        return "/html/member/user_info_change";
    }

    // 마이페이지 - 내 정보 수정 제출
    @PostMapping("/mypage_info_change_submit")
    public String userMyPageChangeSubmit(Model model, HttpSession session, @ModelAttribute UserMemberDto userMemberDto) {
        log.info("[UserMemberController] userMyPageChangeSubmit()");

        if (session.getAttribute("id") == null) {
            return "redirect:/member/sign_in";
        }

        userMemberDto.setId(String.valueOf(session.getAttribute("id")));

        if (!userMemberDto.getProfile_img().isEmpty()) {
            System.out.println(userMemberService.updateUserInfoProfileImg(userMemberDto));
        }

        if (!userMemberDto.getPw().isEmpty() && userMemberService.insertUserSignUpPwConfirm(userMemberDto)) {
            System.out.println(userMemberService.updateUserInfoPw(userMemberDto));
        }

        if (!userMemberDto.getPhone().isEmpty()) {
            System.out.println(userMemberService.updateUserInfoPhone(userMemberDto));
        }

        return "redirect:/member/mypage";
    }

    // 마이페이지 - 회원탈퇴
    @GetMapping("/mypage_account_del")
    public String userMyPageAccountDel(HttpSession session) {
        log.info("[UserMemberController] userMyPageAccountDel()");

        if(session.getAttribute("id") == null){
            return "redirect:/member/sign_in";
        }

        return "/html/member/user_info_del_account";
    }

    // 마이페이지 - 회원탈퇴 비밀번호 확인
    @PostMapping("/mypage_account_del_pw_check")
    public String userMyPageAccountDelPwCheck(HttpSession session, UserMemberDto userMemberDto, Model model) {
        log.info("[UserMemberController] userMyPageAccountDelPwCheck()");

        if(session.getAttribute("id") == null){
            return "redirect:/member/sign_in";
        }

        userMemberDto.setId(String.valueOf(session.getAttribute("id")));

        if(!userMemberService.selectUserSignIn(userMemberDto)){
            model.addAttribute("error", true);
            return "/html/member/user_info_del_account";
        }

        userMemberService.updateUserAccountDel(userMemberDto);

        return "/html/member/user_info_del_account_result";
    }
}