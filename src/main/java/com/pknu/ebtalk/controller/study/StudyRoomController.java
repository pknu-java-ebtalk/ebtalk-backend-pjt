package com.pknu.ebtalk.controller.study;

import com.pknu.ebtalk.dto.member.UserMemberDto;
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
    public String studyRoomForm(@RequestParam int no, Model model, HttpSession session) {
        log.info("[StudyRoomController] studyRoomForm()");

        if(session.getAttribute("loginUser") == null){
            return "redirect:/member/sign_in";
        }


        model.addAttribute("study_no", no);

        return "/html/study/study_room";
    }

    /*
     * 스터디룸 등록
     */
    @ResponseBody
    @PostMapping("/study_register_confirm")
    public int studyRoomRegisterConfirm(StudyRoomDto studyRoomDto, HttpSession session) {
        log.info("[StudyRoomController] studyRoomRegisterConfirm()");

        // 세션 연결
        UserMemberDto loginedUserDto = (UserMemberDto) session.getAttribute("loginUser");
        studyRoomDto.setUser_id(loginedUserDto.getId());

         return studyRoomService.insertStudyRoomConfirm(studyRoomDto);

    }

    /*
     * 스터디룸 리스트
     */
    @ResponseBody
    @GetMapping(value = {"/memoir_list"})
    public List<StudyRoomDto> showMemoirAllList(@RequestParam int study_no) {
        log.info("[StudyRoomController] showMemoirAllList()");

        return studyRoomService.selectStudyRoomAllList(study_no);

    }

    /*
     * 스터디룸 리스트 - aside 이름 가져오기
     */
    @ResponseBody
    @GetMapping(value = {"/memoir_list_mate"})
    public List<StudyRoomDto> showMemoirListMate(@RequestParam int study_no) {
        log.info("[StudyRoomController] showMemoirListMate()");

        return studyRoomService.selectStudyRoomAllListMateName(study_no);

    }

    /*
     * 스터디룸 수정
     */
    @ResponseBody
    @PutMapping(value = {"/study_modify_confirm"})
    public String studyRoomModifyConfirm(StudyRoomDto studyRoomDto, HttpSession session) {
        log.info("[StudyRoomController] studyRoomModifyConfirm()");

        // 세션에서 로그인한 사용자 들고오기
        UserMemberDto loginedUserDto = (UserMemberDto) session.getAttribute("loginUser");
        studyRoomDto.setUser_id(loginedUserDto.getId());

        log.info(studyRoomDto.getContent());
        log.info(studyRoomDto.getNo());

        return studyRoomService.updateStudyRoomConfirm(studyRoomDto);

    }

    /*
     * 스터디룸 삭제
     */
    @ResponseBody
    @DeleteMapping(value = {"/study_delete_confirm"})
    public int studyRoomDeleteConfirm(StudyRoomDto studyRoomDto, HttpSession session) {
        log.info("[StudyRoomController] studyRoomDeleteConfirm()");

        UserMemberDto loginedUserDto = (UserMemberDto) session.getAttribute("loginUser");
        studyRoomDto.setUser_id(loginedUserDto.getId());

        return studyRoomService.deleteStudyRoomConfirm(studyRoomDto);

    }
}
