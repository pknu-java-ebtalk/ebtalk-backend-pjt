package com.pknu.ebtalk.controller.chatting.chatresearch;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
class ChatController {
    @GetMapping(value={"/ChattingResearch"})

    public String ChattingResearch(){
        System.out.println("ChattingResearch");

        String nextPage = "/html/chatting/ChattingResearch";

        return nextPage;
    }
}
