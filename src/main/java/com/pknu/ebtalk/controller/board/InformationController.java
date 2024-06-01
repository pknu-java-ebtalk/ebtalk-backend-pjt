package com.pknu.ebtalk.controller.board;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Log4j2
@Controller
@RequiredArgsConstructor
@RequestMapping("/information")         //정보 게시판 form
public class InformationController {

    @GetMapping(value = {"/information_board_form"})
    public String informationBoard() {
        log.info("[InformationController] informationController()");

        String informationBoard = "/html/board/information_board";
        return informationBoard;
    }

    @GetMapping(value = {"/information_write_form"})
    public String informationWrite() {

        String informationWritePage = "/html/board/information_write";
        return informationWritePage;
    }

    @GetMapping(value = {"/information_view_form"})
    public String informationView() {
        String informationViewPage = "/html/board/information_view";
        return informationViewPage;
    }


    @GetMapping(value = {"/information_eidt_form"})
    public String informationEidt() {
        String informationEidtpage = "/html/board/information_eidt";
        return informationEidtpage;
    }
}