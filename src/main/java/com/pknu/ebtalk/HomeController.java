package com.pknu.ebtalk;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {
    @GetMapping(value={"", "/"})
    @ResponseBody
    public String home() {
        System.out.println("[HomeController] home()");

        return "home";

    }

}