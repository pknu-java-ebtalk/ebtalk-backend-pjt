package com.pknu.ebtalk.controller.chatting.chatresearch;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.socket.config.annotation.EnableWebSocket;


@Controller
class ChatController {
    // http://localhost:8090/ChattingResearch
    @GetMapping(value={"/ChattingResearch"})

    public String ChattingResearch(){
        System.out.println("ChattingResearch");

        String nextPage = "/html/chatting/ChattingResearch";

        return nextPage;
    }
}
