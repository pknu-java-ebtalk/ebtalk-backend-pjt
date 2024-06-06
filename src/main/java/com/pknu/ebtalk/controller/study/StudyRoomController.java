package com.pknu.ebtalk.controller.study;

import com.pknu.ebtalk.dto.study.StudyRoomDto;
import com.pknu.ebtalk.service.study.StudyRoomService;
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
    @ResponseBody
    @PostMapping("/study_register_confirm")
    public int studyRoomRegisterConfirm(StudyRoomDto studyRoomDto, HttpSession session) {
        log.info("[StudyRoomController] studyRoomRegisterConfirm()");

        // 세션에서 로그인한 사용자 들고오기
//        UserMemberDto userMemberDto =  (UserMemberDto) session.getAttribute("id");
//        studyRoomDto.setUser_id(userMemberDto.getId());

        studyRoomDto.setUser_id("eunji123");

         return studyRoomService.insertStudyRoomConfirm(studyRoomDto);

    }

    /*
     * 스터디룸 리스트
     */
    @ResponseBody
    @GetMapping(value = {"/memoir_list"})
    public List<StudyRoomDto> showMemoirAllList(@RequestParam int study_no, Model model) {
        log.info("[StudyRoomController] showMemoirAllList()");

        return studyRoomService.selectStudyRoomAllList(study_no);

    }

    /*
     * 스터디룸 수정
     */
    @ResponseBody
    @PutMapping(value = {"/study_modify_confirm"})
    public String studyRoomModifyConfirm(StudyRoomDto studyRoomDto, HttpSession session) {
        log.info("[StudyRoomController] studyRoomModifyConfirm()");

        studyRoomDto.setUser_id("eunji123");
        log.info(studyRoomDto.getContent());
        log.info(studyRoomDto.getNo());

        return studyRoomService.updateStudyRoomConfirm(studyRoomDto);

    }
}
