package com.pknu.ebtalk.controller.study;

import com.pknu.ebtalk.dto.study.StudyDto;
import com.pknu.ebtalk.service.study.StudyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Log4j2
@Controller
@RequiredArgsConstructor
@RequestMapping("/study")
public class StudyController {

    private final StudyService studyService;

    /*
     *  스터디 모집 등록
     */
    @GetMapping(value={ "/study_register_form"})
    public String studyRegister() {
        log.info("[StudyController] studyRegister()");

        String nextPage = "/html/study/study_register";

        return nextPage;

    }

    @PostMapping(value = {"/study_register_confirm"})
    public Object studyRegisterConfirm(StudyDto studyDto) {
        log.info("[StudyController] studyRegisterConfirm()");

        studyDto.setUser_id("eunji123");

        studyService.insertStudyConfirm(studyDto);

        ModelAndView modelAndView = new ModelAndView();

        modelAndView.setViewName("/html/study/study_register_detail_writer");

        return modelAndView;

    }
}
