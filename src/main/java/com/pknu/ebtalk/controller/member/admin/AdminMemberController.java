package com.pknu.ebtalk.controller.member.admin;

import com.pknu.ebtalk.dto.member.UserMemberDto;
import com.pknu.ebtalk.service.member.admin.AdminMemberService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Log4j2
@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminMemberController {

    private final AdminMemberService adminMemberService;

    // 관리자 페이지로 이동
    @GetMapping("/approve_list")
    public String approveList(Model model, HttpSession session) {
        log.info("[AdminMemberController] approveList()");

        if(session.getAttribute("loginUser") == null){
            return "redirect:/member/sign_in";
        }

        int count = adminMemberService.selectUserApproveWattingCount();
        List<UserMemberDto> wattingUserList = adminMemberService.selectUserApproveWatting();

        model.addAttribute("count", count);
        model.addAttribute("wattingUserList", wattingUserList);

        return "/html/member/admin_approve_list";
    }

    @PostMapping("/approve_list_approve")
    public String userReqApprove(@RequestParam("id") String id){
        log.info("[AdminMemberController] userReqApprove()");

        adminMemberService.updateApproveYnToY(id);

        return "redirect:/admin/approve_list";
    }

    @PostMapping("/approve_list_refuse")
    public String userReqRefuse(@RequestParam("id") String id){
        log.info("[AdminMemberController] userReqRefuse()");

        adminMemberService.deleteUser(id);

        return "redirect:/admin/approve_list";
    }
}