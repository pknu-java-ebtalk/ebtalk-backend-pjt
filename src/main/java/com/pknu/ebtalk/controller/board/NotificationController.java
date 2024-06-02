package com.pknu.ebtalk.controller.board;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Log4j2
@Controller
@RequiredArgsConstructor
@RequestMapping("/notification")        //공지사항 게시판 form
public class NotificationController {

    @GetMapping(value = {"/notification_board_form"})
    public String notificationBoard(){
        log.info("[NotificationController] notificationController()");

        String notificationBoard = "/html/board/board";
        return notificationBoard;
    }

    @GetMapping(value = {"/notification_write_form"})
    public String notificationWrite(){

        String notificationWritePage = "/html/board/board_write";
        return notificationWritePage;
    }

    @GetMapping(value = {"/notification_view_form"})
    public String notificationView(){

        String notificationViewPage = "/html/board/board_view";
        return notificationViewPage;
    }

    @GetMapping(value = {"/notification_edit_form"})
    public String notificationEdit(){
        String notificationEditPage = "/html/board/board_edit";
        return notificationEditPage;
    }
}
