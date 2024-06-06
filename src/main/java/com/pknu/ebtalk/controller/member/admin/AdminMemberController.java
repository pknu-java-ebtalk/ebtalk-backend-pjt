package com.pknu.ebtalk.controller.member.admin;

import com.pknu.ebtalk.service.member.admin.AdminMemberService;
import com.pknu.ebtalk.service.member.user.UserMemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Log4j2
@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminMemberController {
    private final AdminMemberService adminMemberService;

    @GetMapping("/approve_list")
    public String approveList(Model model) {
        return null;
    }
}
