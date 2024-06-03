package com.pknu.ebtalk.controller.study;

import com.pknu.ebtalk.dto.study.StudyDto;
import com.pknu.ebtalk.service.study.StudyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public String studyRegisterForm() {
        log.info("[StudyController] studyRegister()");

        return "/html/study/study_register";

    }

    @PostMapping(value = {"/study_register_confirm"})
    public String studyRegisterConfirm(StudyDto studyDto, Model model) {
        log.info("[StudyController] studyRegisterConfirm()");

        studyDto.setUser_id("eunji123");

        log.info(studyDto.getContent());

        studyDto = studyService.insertStudyConfirm(studyDto);

        if(studyDto.getNo() != 0){
            model.addAttribute("studyDto", studyDto);

            return "/html/study/study_register_detail";

        } else{
            return "/html/study/study_register_list";

        }
    }

    /*
     * 스터디 모집글 수정
     */
    @GetMapping(value = {"/study_modify_form"})
    public String studyModifyForm(@RequestParam int no, Model model) {
        log.info("[StudyController] studyModifyForm()");

        StudyDto studyDto = studyService.selectStudyInfoByNo(no);

        model.addAttribute("studyDto", studyDto);

        String nextPage = "/html/study/study_modify";

        return nextPage;

    }

    @PutMapping(value = {"/study_modify_confirm"})
    public String studyModifyConfirm(StudyDto studyDto, Model model) {
        log.info("[StudyController] studyModifyConfirm()");
        log.info(studyDto.getNo());

        studyDto = studyService.updateStudyConfirm(studyDto);

        model.addAttribute("studyDto", studyDto);

        return "/html/study/study_register_detail";

    }

    /*
     * 스터디 모집글 리스트
     */
    @GetMapping(value = {"/study_list"})
    public String showStudyAllList(Model model){
        log.info("[StudyController] showStudyAllList()");

        model.addAttribute("studyDtos", studyService.selectStudyAllList());

        return "/html/study/study_register_list";
    }

    /*
     * 스터디 모집글 상세 페이지
     */
    @GetMapping(value = {"/study_detail"})
    public String showStudyDetail(@RequestParam int no,Model model){
        log.info("[StudyController] showStudyDetail()");

        model.addAttribute("studyDto", studyService.selectStudyInfoByNo(no));

        return "/html/study/study_register_detail";

    }

    /*
     * 스터디 모집글 삭제
     */
    @GetMapping(value = {"/study_delete_confirm"})
    public String delelteStudyConfirm(@RequestParam int no){
        log.info("[StudyController] deleteStudyConfirm()");

        studyService.deleteStudyConfirm(no);

        return "redirect:/study/study_list";

    }
    
    /*
     * 스터디 관리 페이지 - 진행중인 스터디 리스트
     */
    @GetMapping(value = {"/study_in_progress_list"})
    public String showStudyInProgressList(Model model){
        log.info("[StudyController] showStudyInProgressList()");

        StudyDto studyDto = new StudyDto();
        studyDto.setUser_id("eunji123");

//        List<StudyDto> studyDtos =

        model.addAttribute("studyDtos", studyService.selectStudyInProgressByUId(studyDto));

        return "/html/study/study_in_progress";
    }

    /*
     * 스터디 관리 페이지 - 스터디 신청 목록
     */
    @GetMapping(value = {"/study_application_list"})
    public String showStudyApplicationList(Model model){
        log.info("[StudyController] showStudyApplicationList()");

        StudyDto studyDto = new StudyDto();
        studyDto.setUser_id("eunji123");

        studyService.selectStudyApplicationListById(studyDto);

        return "/html/study/study_application_list";

    }


}
