package com.pknu.ebtalk.controller.study;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Log4j2
@Controller
@RequiredArgsConstructor
@RequestMapping("/studyroom")
public class StudyRoomController {

    public String studyRoomForm(@RequestParam int study_no, Model model) {


        model.addAttribute("study_no", study_no);
        String nextPage = "";

        return nextPage;
    }

    @GetMapping(value = {"/memoir_list"})
    public String showMemoirAllList(@RequestParam int study_no, Model model) {

        return "";

    }

}
