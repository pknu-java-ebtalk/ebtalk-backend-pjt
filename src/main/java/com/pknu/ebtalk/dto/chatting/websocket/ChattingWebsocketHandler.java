package com.pknu.ebtalk.dto.chatting.websocket;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.pknu.ebtalk.dto.chatting.ChatRoomUserDTO;
import com.pknu.ebtalk.dto.chatting.MessageDTO;
import com.pknu.ebtalk.service.chatting.ChattingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class ChattingWebsocketHandler extends TextWebSocketHandler {
    private Logger logger = LoggerFactory.getLogger(ChattingWebsocketHandler.class);

    @Autowired
    private ChattingService service;
    // WebSocketSession : 클라이언트 - 서버간 전이중통신을 담당하는 객체 (JDBC Connection과 유사)
    // 클라이언트의 최초 웹소켓 요청 시 생성
    private Set<WebSocketSession> sessions = Collections.synchronizedSet(new HashSet<WebSocketSession>());
    // synchronizedSet : 동기화된 Set 반환(HashSet은 기본적으로 비동기)
    // -> 멀티스레드 환경에서 하나의 컬렉션에 여러 스레드가 접근하여 의도치 않은 문제가 발생되지 않게 하기 위해
    //    동기화를 진행하여 스레드가 여러 순서대로 한 컬렉션에 순서대로 접근할 수 있게 변경

    // afterConnectionEstablished - 클라이언트와 연결이 완료되고, 통신할 준비가 되면 실행
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        sessions.add(session);
    }

    // handlerTextMessage - 클라이언트로부터 텍스트 메시지를 받았을 때 실행
    // WebSocketSession session = 현재 웹소켓 세션, TextMessage message = 수신한 텍스트 메시지
    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        // 전달받은 내용은 JSON 형태의 String
        logger.info("전달받은 내용 : " + message.getPayload());

        //Jackson에서 제공하는 객체
        // JSON String -> VO Object
        ObjectMapper objectMapper = new ObjectMapper();

        MessageDTO msg = objectMapper.readValue(message.getPayload(), MessageDTO.class);

        // Message 객체 확인
        System.out.println(msg);

        // DB 삽입 서비스 호출
        int result = service.insertMessage(msg);

        if (result > 0) { // 포맷팅된 날짜/시간 값을 저장
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM.dd hh:mm");
            Date currentDate = new Date();
            msg.setCREATE_AT(currentDate);
        }

        // 전역변수로 선언된 session에는 접속중인 모든 회원의 세션 정보가 담겨 있다.
        for (WebSocketSession s : sessions) {
            // WebSocketSession은 HttpSession의 속성을 가로채서 똑같이 가지고 있기 떄문에
            // 회원의 정보를 나타내는 loginMember도 가지고 있음

            // 로그인된 회원 정보 중 회원 번호 얻어오기
            String loginID = (((ChatRoomUserDTO)s.getAttributes().get("ID")).getUSER_ID());
            logger.debug("loginID : " + loginID);

            // 로그인 상태인 회원 중 TO_ID(수신자 아이디)가 일치하는 회원에게 메시지 전달
            if (loginID == msg.getTO_ID() || loginID == msg.getFROM_ID()){
                s.sendMessage(new TextMessage(new Gson().toJson(msg)));
            }

        }
    }

    // afterConnectionClosed - 클라이언트와 연결이 종료되면 실행
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        sessions.remove(session);
    }
}
