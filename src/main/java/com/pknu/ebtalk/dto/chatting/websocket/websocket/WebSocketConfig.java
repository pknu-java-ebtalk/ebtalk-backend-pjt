package com.pknu.ebtalk.dto.chatting.websocket.websocket;


import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;


@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) { //클라이언트가 서버에 웹소켓 연결을 시도할 수 있는 경로
        registry.addEndpoint("/ws").setAllowedOrigins("*").withSockJS();
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.setApplicationDestinationPrefixes("/app"); //클라이언트가 서버로 메시지를 보낼 때 사용할 수 있는 목적지(prefix)의 접두어를 설정
        registry.enableSimpleBroker("/topic","/queue");
        // topic : 한명이 message를 발행했을 떄 해당 토픽을 구독하고 있는 n명에게 메시지를 뿌림
        // queue : 한명이 message를 발행했을 떄 발행한 한 명에게 다시 정보를 보내는 경우 사용
    }
}
