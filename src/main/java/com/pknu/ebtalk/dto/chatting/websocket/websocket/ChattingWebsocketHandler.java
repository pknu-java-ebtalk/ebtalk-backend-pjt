package com.pknu.ebtalk.dto.chatting.websocket.websocket;



import com.pknu.ebtalk.dto.chatting.MessageDto;
import com.pknu.ebtalk.service.chatting.IChattingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Controller
public class ChattingWebsocketHandler implements WebSocketMessageBrokerConfigurer {
    @Autowired  // 필요한 의존 객체의 “타입"에 해당하는 빈을 찾아 주입한다 =  개발자가 직접 의존 객체를 생성하고 관리, 스프링이 자동으로 적절한 빈을 찾아서 주입해 줌.
    private IChattingService IChattingService;

    @MessageMapping("/chat.sendMessage")
    @SendTo("/topic/public") // WebSocketConfig 클래스에 있는 configureMessageBroker메서드 안의 경로와 일치.
    public MessageDto sendMessage(MessageDto messageDto, SimpMessageHeaderAccessor headerAccessor){

        return messageDto;
    }
}
