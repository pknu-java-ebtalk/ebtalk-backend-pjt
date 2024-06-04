package com.pknu.ebtalk.controller.board;

import com.pknu.ebtalk.dto.board.BoardDto;
import com.pknu.ebtalk.service.board.NotificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Log4j2
@Controller
@RequiredArgsConstructor
@RequestMapping("/notification")        //공지사항 게시판 form
public class NotificationController {
    private final NotificationService notificationService;

    
//    게시글 리스트
    @GetMapping(value = {"/notification_board_form"})
    public String notificationBoard() {
        log.info("[NotificationController] notificationController()");

        String notificationBoard = "/html/board/board";
        return notificationBoard;
    }

    
//    게시판 글쓰기 기능
    @GetMapping(value = {"/notification_write_form"})           //스프링에서는 주소 값을 가지고 메서드를 찾아간다.
    public String notificationWrite() {

        String notificationWritePage = "/html/board/board_write";
        return notificationWritePage;       //프론트로 주소를 보내준다.
    }
    
    // write html에서 등록 버튼을 눌리게 된다면 실행
    @PostMapping(value = {"/notification_write_confirm"})
    public String notificationWriteConfirm(Model model, BoardDto boardDto) {
        //model은 프론트로 값을 보내는데 key , value 형태로 보냄
//        key를 통해 value 안에 값을 가져올 수 있다.
        boardDto.setUser_id("jkh");
        boardDto = notificationService.insertNotificationInfo(boardDto);
        if (boardDto.getNo() != 0) {        // 값이 있는 경우 - 타입이 int 여서 null 대신 0 을 입력한것
            model.addAttribute("boardDto", boardDto);       // 프론트로 값을 보냄
            return "/html/board/board_view";
        }
        return "/html/board/board_write";       // 이거는 아무거나 해도 상관없음
    }

    
//    게시글 보기
    @GetMapping(value = {"/notification_view_form"})
    public String notificationView() {

        String notificationViewPage = "/html/board/board_view";
        return notificationViewPage;
    }

    
//    게시글 수정
    @GetMapping(value = {"/notification_edit_form"})
    public String notificationEdit(@RequestParam int no, Model model) {
        log.info("[NotificationController] notificationEditForm()" );

        BoardDto boardDto = notificationService.selectNotificationInfoByNo(no);
        model.addAttribute("boardDto", boardDto);

        String notificationEditPage = "/html/board/board_edit";
        return notificationEditPage;
    }
}