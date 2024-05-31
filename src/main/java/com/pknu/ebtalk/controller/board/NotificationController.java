package com.pknu.ebtalk.controller.board;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Log4j2
@Controller
@RequiredArgsConstructor
@RequestMapping("/notification")
public class NotificationController {

    @GetMapping(value = {"/board_register_form"})
    public String boardRegister(){
        log.info("[NotificationController] notificationController()");

        String nextPage = "/html/board/board";
        return nextPage;
    }
}
