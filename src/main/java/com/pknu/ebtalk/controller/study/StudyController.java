package com.pknu.ebtalk.controller.study;

import com.pknu.ebtalk.dto.member.UserMemberDto;
import com.pknu.ebtalk.dto.study.StudyDto;
import com.pknu.ebtalk.service.study.StudyService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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
    public String studyRegisterForm(HttpSession session) {
        log.info("[StudyController] studyRegister()");

        if(session.getAttribute("loginUser") == null){
            return "redirect:/member/sign_in";
        }

        return "/html/study/study_register";

    }

    @PostMapping(value = {"/study_register_confirm"})
    public String studyRegisterConfirm(StudyDto studyDto, Model model, HttpSession session) {
        log.info("[StudyController] studyRegisterConfirm()");

        if(session.getAttribute("loginUser") == null){
            return "redirect:/member/sign_in";
        }

        // 세션 연결
        UserMemberDto loginedUserDto = (UserMemberDto) session.getAttribute("loginUser");
        studyDto.setUser_id(loginedUserDto.getId());

        studyDto = studyService.insertStudyConfirm(studyDto);
        int no = studyDto.getNo();

        if(studyDto.getNo() != 0){
            model.addAttribute("studyDto", studyDto);

            return "redirect:/study/study_detail?no=" + no;

        } else{
            return "/html/study/study_register_list";

        }
    }

    /*
     * 스터디 모집글 수정
     */
    @GetMapping(value = {"/study_modify_form"})
    public String studyModifyForm(@RequestParam int no, Model model, HttpSession session) {
        log.info("[StudyController] studyModifyForm()");

        if(session.getAttribute("loginUser") == null){
            return "redirect:/member/sign_in";
        }

        StudyDto studyDto = studyService.selectStudyInfoByNo(no);

        model.addAttribute("studyDto", studyDto);

        return "/html/study/study_modify";

    }

    @PutMapping(value = {"/study_modify_confirm"})
    public String studyModifyConfirm(StudyDto studyDto, Model model, HttpSession session) {
        log.info("[StudyController] studyModifyConfirm()");
        log.info(studyDto.getNo());

        if(session.getAttribute("loginUser") == null){
            return "redirect:/member/sign_in";
        }

        // 세션 연결
        UserMemberDto loginedUserDto = (UserMemberDto) session.getAttribute("loginUser");
        studyDto.setUser_id(loginedUserDto.getId());
        int no = studyDto.getNo();

        studyDto = studyService.updateStudyConfirm(studyDto);

        if(studyDto != null) {
            model.addAttribute("studyDto", studyDto);

            return "redirect:/study/study_detail?no=" + no;
        }

        return "redirect:/study/study_list";

    }

    /*
     * 스터디 모집글 리스트
     */
    @GetMapping(value = {"/study_list"})
    public String showStudyAllList(Model model, HttpSession session){
        log.info("[StudyController] showStudyAllList()");

        if(session.getAttribute("loginUser") == null){
            return "redirect:/member/sign_in";
        }

        model.addAttribute("studyDtos", studyService.selectStudyAllList());

        return "/html/study/study_register_list";
    }

    /*
     * 스터디 모집글 상세 페이지
     */
    @GetMapping(value = {"/study_detail"})
    public String showStudyDetail(@RequestParam int no,Model model, HttpSession session){
        log.info("[StudyController] showStudyDetail()");

        if(session.getAttribute("loginUser") == null){
            return "redirect:/member/sign_in";
        }

        model.addAttribute("studyDto", studyService.selectStudyInfoByNo(no));

        return "/html/study/study_register_detail";

    }

    /*
     * 스터디 모집글 삭제
     */
    @GetMapping(value = {"/study_delete_confirm"})
    public String delelteStudyConfirm(@RequestParam int no, HttpSession session){
        log.info("[StudyController] deleteStudyConfirm()");

        if(session.getAttribute("loginUser") == null){
            return "redirect:/member/sign_in";
        }

        // 세션 연결
        UserMemberDto loginedUserDto = (UserMemberDto) session.getAttribute("loginUser");
        StudyDto studyDto = new StudyDto();
        studyDto.setUser_id(loginedUserDto.getId());
        studyDto.setNo(no);

        studyService.deleteStudyConfirm(studyDto);

        return "redirect:/study/study_list";

    }

    /*
     * 스터디 신청
     */
    @ResponseBody
    @GetMapping(value = {"/study_approval"})
    public Map<String, String> studyApproval(@RequestParam int no, HttpSession session){
        log.info("[StudyController] studyApproval()");

        // 세션 연결
        UserMemberDto loginedUserDto = (UserMemberDto) session.getAttribute("loginUser");
        StudyDto studyDto = new StudyDto();
        studyDto.setUser_id(loginedUserDto.getId());
        studyDto.setNo(no);

        return studyService.insertStudyApproval(studyDto);

    }
    
    /*
     * 스터디 관리 페이지 - 진행중인 스터디 리스트
     */
    @GetMapping(value = {"/study_in_progress_list"})
    public String showStudyInProgressList(Model model, HttpSession session){
        log.info("[StudyController] showStudyInProgressList()");

        if(session.getAttribute("loginUser") == null){
            return "redirect:/member/sign_in";
        }

        // 세션 연결
        UserMemberDto loginedUserDto = (UserMemberDto) session.getAttribute("loginUser");

        model.addAttribute("studyDtos", studyService.selectStudyInProgressByUId(loginedUserDto.getId()));

        return "/html/study/study_in_progress";
    }

    /*
     * 스터디 관리 페이지 - 스터디 신청 목록
     */
    @GetMapping(value = {"/study_application_list"})
    public String showStudyApplicationList(Model model, HttpSession session){
        log.info("[StudyController] showStudyApplicationList()");

        if(session.getAttribute("loginUser") == null){
            return "redirect:/member/sign_in";
        }

        // 세션 연결
        UserMemberDto loginedUserDto = (UserMemberDto) session.getAttribute("loginUser");
        String user_id = loginedUserDto.getId();

        List<StudyDto> studyDtos = studyService.selectStudyApplicationListById(user_id);

        model.addAttribute("studyDtos", studyDtos);

        return "/html/study/study_application_list";

    }

    /*
     * 스터디 관리 페이지 - 스터디 신청 승인 처리
     */
    @PostMapping(value = {"/study_application_list_confirm"})
    @ResponseBody
    public Map<String, Object> studyApplicationListConfirm(@RequestParam String user_id, @RequestParam int study_no){
        log.info("[StudyController] studyApplicationListConfirm()");

        StudyDto studyDto = new StudyDto();
        studyDto.setUser_id(user_id);
        studyDto.setNo(study_no);

        Map<String, Object> map = studyService.updateStudyApplicationListById(studyDto);

        return map;

    }
}