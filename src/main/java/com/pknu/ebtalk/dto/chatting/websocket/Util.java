package com.pknu.ebtalk.dto.chatting.websocket;

public class Util {
        // XSS(Cross-Site Scripting) 공격을 방지하는 메서드
        public static String XSSHandling(String content) {
            return content.replaceAll("<","&lt;").replaceAll(">","&gt;");
    }
}
