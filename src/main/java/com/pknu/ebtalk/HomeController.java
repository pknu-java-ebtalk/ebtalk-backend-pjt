package com.pknu.ebtalk;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {
    @GetMapping(value={"", "/"})
    public String home(HttpSession session) {
        System.out.println("[HomeController] home()");

        if(session.getAttribute("id") != null){
            return "/html/home/home";
        }

        return "redirect:/member/sign_in";

    }
}