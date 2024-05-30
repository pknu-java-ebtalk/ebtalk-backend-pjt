package com.pknu.ebtalk.controller.study;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class studyRegister {

        @GetMapping(value={ "/studyRegister"})
        public String studyRegister() {
            System.out.println("[HomeController] studyRegister()");

            String nextPage = "/html/study/study_register";

            return nextPage;

        }
}
