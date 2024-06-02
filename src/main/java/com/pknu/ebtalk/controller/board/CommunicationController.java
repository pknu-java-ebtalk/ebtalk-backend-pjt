package com.pknu.ebtalk.controller.board;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Log4j2
@Controller
@RequiredArgsConstructor
@RequestMapping("/communication")
public class CommunicationController {

    @GetMapping(value = {"/communication_board_form"})
    public String communicationBoard(){
        log.info("[CommunicationController] communicationController()");

        String communicationBoard = "/html/board/Communication_board";
        return communicationBoard;
    }

    @GetMapping(value = {"/communication_view_form"})
    public String communicationWrite(){

        String communicationWritePage = "/html/board/Communication_view";
        return communicationWritePage;

    }

    @GetMapping(value = {"/communication_write_form"})
    public String communicationView(){

        String communicationViewPage = "/html/board/Communication_board_write";
        return communicationViewPage;
    }

    @GetMapping(value = {"/communication_edit_form"})
    public String communicationEdit(){
        String communicationEditPage = "/html/board/Communication_board_edit";
        return communicationEditPage;
    }
}

