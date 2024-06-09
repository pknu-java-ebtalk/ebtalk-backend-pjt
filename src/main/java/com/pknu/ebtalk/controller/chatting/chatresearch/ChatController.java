package com.pknu.ebtalk.controller.chatting.chatresearch;

import com.pknu.ebtalk.dto.chatting.ChatRoomDto;
import com.pknu.ebtalk.dto.chatting.ChatRoomUserDto;
import com.pknu.ebtalk.dto.member.UserMemberDto;
import com.pknu.ebtalk.service.chatting.ChattingService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@Log4j2
@RequiredArgsConstructor
@RequestMapping("/chat")
class ChatController {
    private final ChattingService chattingService;

    // http://localhost:8090/member/sign_in
    // http://localhost:8090/chat/ChattingResearch
    @GetMapping(value={"/ChattingResearch"})
    public String ChattingResearchGet(Model model, HttpSession session) {
        log.info("[ChatController] ChattingResearchGet()");

        UserMemberDto userMemberDto = (UserMemberDto) session.getAttribute("loginUser");

        if (userMemberDto == null) {
            log.error("No logged in user found in session");
            return "redirect:/member/sign_in"; // 로그인 페이지로 리다이렉트
        }

        log.info("Logged in user: {}", userMemberDto);

        ChatRoomUserDto checkUser = new ChatRoomUserDto();
        checkUser.setId(userMemberDto.getId());
        checkUser.setName(userMemberDto.getName());

        log.info("Searching for user: {}", checkUser);

        List<ChatRoomUserDto> users = chattingService.selectChatName(checkUser);

        model.addAttribute("users", users);

        return "/html/chatting/ChattingResearch";
    }

    @PostMapping(value = "/ChattingResearchPost")
    @ResponseBody
    public Map<String,Object> ChattingResearchPost(@RequestBody Map<String, Object> request) {
        log.info("[ChatController] ChattingResearchPost()");
        String userName = (String) request.get("name");
        log.info("Received userName: {}", userName);

        ChatRoomUserDto param = new ChatRoomUserDto();
        param.setName(userName);

        log.info("Searching with param: {}", param); // 로그 추가

        List<ChatRoomUserDto> users = chattingService.selectChatName(param);
        log.info("isNull={}", users.isEmpty());
        log.info("username={}", userName);

        Map<String,Object> response = new HashMap<>();
        response.put("success", true);
        response.put("users", users);

        log.info("Response to be sent: {}", response); // 응답 로그 추가

        return response;
    }

    // 체팅방 생성 및 이동
    @PostMapping(value = "/createChatRoom")
    public String createChatRoom(@RequestParam String chatUserName, HttpSession session, Model model) {
        log.info("[ChatController] createChatRoom()");

        UserMemberDto userMemberDto = (UserMemberDto) session.getAttribute("loginUser");

        if (userMemberDto == null) {
            log.error("No logged in user found in session");
            return "redirect:/member/sign_in"; // 로그인 페이지로 리다이렉트
        }


        ChatRoomDto chatRoom = new ChatRoomDto("Chat with " + chatUserName, "1:1");
        chattingService.createChatRoom(chatRoom);

        ChatRoomUserDto chatRoomUser = new ChatRoomUserDto();
        chatRoomUser.setChat_room_id(chatRoom.getNo());
        chatRoomUser.setId(userMemberDto.getId());
        chatRoomUser.setName(userMemberDto.getName());
        chattingService.addChatRoomUser(chatRoomUser);

        ChatRoomUserDto chatUser = new ChatRoomUserDto();
        chatUser.setChat_room_id(chatRoom.getNo());
        chatUser.setName(chatUserName);
        // chatUser의 id는 조회해서 설정해야 함
        List<ChatRoomUserDto> users = chattingService.selectChatName(chatUser);
        if (!users.isEmpty()) {
            chatUser.setId(users.get(0).getId());
            chattingService.addChatRoomUser(chatUser);
        }
        model.addAttribute("roomId", chatRoom.getNo());
        return "redirect:/chat/chatRoom/" + chatRoom.getNo();
    }

    @GetMapping(value = "/chatRoom/{roomId}")
    public String ChattingRoomGet(@PathVariable int roomId, Model model, HttpSession session){

        UserMemberDto userMemberDto = (UserMemberDto) session.getAttribute("loginUser");

        if (userMemberDto == null) {
            log.error("No logged in user found in session");
            return "redirect:/login"; // 로그인 페이지로 리다이렉트
        }

        model.addAttribute("roomId", roomId);

        return "/html/chatting/ChattingRoom";
    }
}