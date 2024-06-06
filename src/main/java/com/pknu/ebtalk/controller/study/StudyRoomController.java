package com.pknu.ebtalk.controller.study;

import com.pknu.ebtalk.dto.member.UserMemberDto;
import com.pknu.ebtalk.dto.study.StudyRoomDto;
import com.pknu.ebtalk.service.study.IStudyRoomService;
import com.pknu.ebtalk.service.study.StudyRoomService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Log4j2
@Controller
@RequiredArgsConstructor
@RequestMapping("/studyroom")
public class StudyRoomController {
    private final StudyRoomService studyRoomService;

    /*
     * 스터디룸 페이지
     */
    @GetMapping("")
    public String studyRoomForm(@RequestParam int no, Model model) {
        log.info("[StudyRoomController] studyRoomForm()");

        model.addAttribute("no", no);

        return "/html/study/study_room";
    }

    /*
     * 스터디룸 등록
     */
    @GetMapping("/study_register_confirm")
    public void studyRoomRegisterConfirm(@RequestParam String study_no, @RequestParam int m_category_no, StudyRoomDto studyRoomDto, HttpSession session) {
        log.info("[StudyRoomController] studyRoomRegisterConfirm()");

        // 세션에서 로그인한 사용자 들고오기
//        UserMemberDto userMemberDto =  (UserMemberDto) session.getAttribute("id");
//        studyRoomDto.setUser_id(userMemberDto.getId());

        studyRoomDto.setUser_id("eunji123");

         studyRoomService.insertStudyRoomConfirm(studyRoomDto);

    }

    @ResponseBody
    @GetMapping(value = {"/memoir_list"})
    public List<StudyRoomDto> showMemoirAllList(@RequestParam int study_no, Model model) {
        log.info("[StudyRoomController] showMemoirAllList()");

        return studyRoomService.selectStudyRoomAllList(study_no);

    }

}
